package DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DBDelete {
    public static void deletePerson(String name) throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getConnection();
        PreparedStatement psta=connection.prepareStatement("delete  from friend where vcName=?");
        psta.setString(1,name);
        psta.execute();
    }
    public static void deleteUser(String field) throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getConnection();
        String sql="alter table userinformation drop column "+field;
        Statement statement=connection.createStatement();
        statement.execute(sql);
    }
    public static void deletePsysical(String field) throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getConnection();
        String sql="alter table physicalinformation drop column "+field;
        Statement statement=connection.createStatement();
        statement.execute(sql);
    }
    public static void deleteMental(String field) throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getConnection();
        String sql="alter table mentalinformation drop column "+field;
        Statement statement=connection.createStatement();
        statement.execute(sql);
    }

    public static void deletegraph(String field) throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getConnection();
        String sql="alter table graph drop column "+field;
        Statement statement=connection.createStatement();
        statement.execute(sql);
    }
    public static void deleteUserRecord(String No) throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getConnection();
        PreparedStatement psta=connection.prepareStatement("delete  from userinformation where naturalDataNo=?");
        psta.setString(1,No);
        psta.execute();
    }
    public static void deleteMentalRecord(String No) throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getConnection();
        PreparedStatement psta=connection.prepareStatement("delete  from mentalinformation where mentaldataNo=?");
        psta.setString(1,No);
        psta.execute();
    }
    public static void deletePhysicalRecord(String No) throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getConnection();
        PreparedStatement psta=connection.prepareStatement("delete  from physicalinformation where physicaldataNo=?");
        psta.setString(1,No);
        psta.execute();
    }
    public static void deleteGraphRecord(String No) throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getConnection();
        PreparedStatement psta=connection.prepareStatement("delete  from graph where graphId=?");
        psta.setString(1,No);
        psta.execute();
    }
}
