package prepared_statement.common;

import com.mysql.cj.protocol.x.XProtocolRowInputStream;
import entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MyMapper implements IMapper<User>{
    private static MyMapper instance;
    private MyMapper(){

    }

    public static MyMapper getInstance() {
        if (instance ==null){
            instance = new MyMapper();
        }
        return instance;
    }

    @Override
    public User mapRow(ResultSet resultSet) {
        User user = new User();
        try {
            user.setId(resultSet.getLong("id"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setUsername(resultSet.getString("username"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
