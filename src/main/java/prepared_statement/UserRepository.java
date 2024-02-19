package prepared_statement;

import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import prepared_statement.common.AbstractDAO;
import prepared_statement.common.MyMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class UserRepository extends AbstractDAO<User> implements IUserRepository{
    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);
    private Utils utils;
    private static UserRepository instance;

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    private UserRepository() {
        this.utils = Utils.getInstance();
    }

    public List<User> findAllV1() {
        logger.info("find all users");
        StringBuilder sql = new StringBuilder("select * from user");
        List<User> userList = new ArrayList<>();
        Connection connection = this.utils.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        if (connection != null) {
            try {
                statement = connection.prepareStatement(sql.toString());
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                    user.setUsername(resultSet.getString("username"));
                    userList.add(user);
                }

            } catch (SQLException e) {
                logger.error("find all user failed : {" + e.getSQLState() + "} {" + e.getMessage() + "}");
                throw new RuntimeException(e);
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
                    logger.error("close connection failed");
                }
            }
        }

        return userList;
    }

    @Override
    public List<User> findAll() {
        String sql = "select * from user";
        return query(sql, MyMapper.getInstance());
    }

    @Override
    public List<User> findById(Long id) {
        String sql = "select * from user where id  = ?";
        return query(sql, MyMapper.getInstance(), id);
    }


}
