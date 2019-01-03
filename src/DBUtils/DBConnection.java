package DBUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    /**
     * get mysql connection
     * @return
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/psychologyAnalysis"+"?serverTimezone=GMT%2B8","root","zhujian");
        return connection;
    }
}
