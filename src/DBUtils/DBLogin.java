package DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBLogin {
    public static boolean Login(String administratorId,String administratorName,String administratorPwd) throws SQLException, ClassNotFoundException {
        Connection conn=DBConnection.getConnection();
        PreparedStatement psta=conn.prepareStatement("SELECT * from administrator where administratorId=? and administratorName=? and  administratorPwd=?");
        psta.setString(1,administratorId);
        psta.setString(2,administratorName);
        psta.setString(3,administratorPwd);
        ResultSet resultSet=psta.executeQuery();
        resultSet.last();
        if(resultSet.getRow()>0){
            return true;
        }else {
            return false;
        }
    }
}
