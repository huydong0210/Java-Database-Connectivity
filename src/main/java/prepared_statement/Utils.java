package prepared_statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utils {
    private static Utils instance;
    private Utils(){

    }
    public static Utils getInstance() {
        if (instance == null){
            instance = new Utils();
        }
        return instance;
    }
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/eshop", "root","Dong0210@");
        } catch (ClassNotFoundException e){

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
