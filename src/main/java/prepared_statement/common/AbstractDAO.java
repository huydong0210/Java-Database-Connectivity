package prepared_statement.common;

import entity.User;
import prepared_statement.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbstractDAO<T> implements GenericDAO<T> {
    @Override
    public <T> List<T> query(String sql, IMapper<T> mapper, Object... parameters) {
        List<T> results = new ArrayList<T>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Utils.getInstance().getConnection();
            statement = connection.prepareStatement(sql.toString());
            setParameters(statement, parameters);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.add(mapper.mapRow(resultSet));
            }
        } catch (SQLException e) {

        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
            }

        }
        return results;
    }

    private void setParameters(PreparedStatement statement, Object... parameters) {
        try {
            for (int i = 0; i < parameters.length; i++) {
                Object parameter = parameters[i];
                int index = i + 1;
                if (parameter instanceof Long){
                    statement.setLong(index, (Long) parameter);
                } else if (parameter instanceof String){
                    statement.setString(index, (String) parameter);
                } else if (parameter instanceof Boolean){
                    statement.setBoolean(index, (Boolean) parameter);
                } else if (parameter instanceof Array){
                    statement.setArray(index, (Array) parameter);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
