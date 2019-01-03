package DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBRegister {
    public static void registerAccount(String administratorId,String administratorName,String administratorPwd) throws SQLException, ClassNotFoundException {
        Connection conn=DBConnection.getConnection();
        PreparedStatement psta=conn.prepareStatement("INSERT INTO administrator values (?,?,?)");
        psta.setString(1,administratorId);
        psta.setString(2,administratorName);
        psta.setString(3,administratorPwd);
        psta.execute();
    }
}
