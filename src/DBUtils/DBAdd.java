package DBUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBAdd {
    public static void addAdminstratorField(String field) throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getConnection();
        String sql="alter table administrator add "+field;
        Statement statement=connection.createStatement();
        statement.execute(sql);
    }
    public static void addUserField(String field) throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getConnection();
        String sql="alter table userinformation add "+field;
        Statement statement=connection.createStatement();
        statement.execute(sql);
    }
    public static void addPhysicalField(String field) throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getConnection();
        String sql="alter table physicalinformation add "+field;
        Statement statement=connection.createStatement();
        statement.execute(sql);
    }
    public static void addMentalField(String field) throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getConnection();
        String sql="alter table mentalinformation add "+field;
        Statement statement=connection.createStatement();
        statement.execute(sql);
    }
    public static void addGraphField(String field) throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getConnection();
        String sql="alter table graph add "+field;
        Statement statement=connection.createStatement();
        statement.execute(sql);
    }
}
