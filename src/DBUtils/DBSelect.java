package DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeSet;

public class DBSelect {
    public static ResultSet selectAdminstrator() throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement psta = conn.prepareStatement("select * from  administrator");
        ResultSet resultSet = psta.executeQuery();
        return resultSet;
    }
    public static ResultSet selectUser() throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement psta = conn.prepareStatement("select * from  userinformation");
        ResultSet resultSet = psta.executeQuery();
        return resultSet;
    }
    public static ResultSet selectMental() throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement psta = conn.prepareStatement("select * from  mentalinformation");
        ResultSet resultSet = psta.executeQuery();
        return resultSet;
    }
    public static ResultSet selectPhysical() throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement psta = conn.prepareStatement("select * from  physicalinformation");
        ResultSet resultSet = psta.executeQuery();
        return resultSet;
    }
    public static ResultSet selectGraph() throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement psta = conn.prepareStatement("select * from  graph");
        ResultSet resultSet = psta.executeQuery();
        return resultSet;
    }
    public static ResultSet selectAdminstratorField() throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement psta = conn.prepareStatement("select column_name from information_schema.COLUMNS where TABLE_NAME='administrator'");
        ResultSet resultSet = psta.executeQuery();
        return resultSet;
    }

    public static ResultSet selectMentalField() throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement psta = conn.prepareStatement("select column_name from information_schema.COLUMNS where TABLE_NAME='mentalInformation'");
        ResultSet resultSet = psta.executeQuery();
        return resultSet;
    }

    public static ResultSet selectPhysicalField() throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement psta = conn.prepareStatement("select column_name from information_schema.COLUMNS where TABLE_NAME='physicalInformation'");
        ResultSet resultSet = psta.executeQuery();
        return resultSet;
    }

    public static ResultSet selectuserField() throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement psta = conn.prepareStatement("select column_name from information_schema.COLUMNS where TABLE_NAME='userInformation'");
        ResultSet resultSet = psta.executeQuery();
        return resultSet;
    }

    public static ResultSet selectGraphField() throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement psta = conn.prepareStatement("select column_name from information_schema.COLUMNS where TABLE_NAME='graph'");
        ResultSet resultSet = psta.executeQuery();
        return resultSet;
    }

    public static ArrayList<String> selectUserID() throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement psta = conn.prepareStatement("select  naturalDataNo from userinformation ");
        ResultSet resultSet = psta.executeQuery();
        ArrayList<String> list=new ArrayList<>();
        while (resultSet.next()){
            list.add(resultSet.getString(1));
        }
        return list;
    }

    public static ArrayList<String> selectHeartBeat(String s) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement psta = conn.prepareStatement("select  heartbeat from physicalinformation where userId=?");
        psta.setString(1,s);
        ResultSet resultSet = psta.executeQuery();
        ArrayList<String> list=new ArrayList<>();
        while (resultSet.next()){
            list.add(resultSet.getString(1));
        }
        return list;
    }

    public static ArrayList<String> selectBooldPresure(String s) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement psta = conn.prepareStatement("select  bloodPressure from physicalinformation where userId=?");
        psta.setString(1,s);
        ResultSet resultSet = psta.executeQuery();
        ArrayList<String> list=new ArrayList<>();
        while (resultSet.next()){
            list.add(resultSet.getString(1));
        }
        return list;
    }

    public static TreeSet<String> selectField(String newValue) throws SQLException, ClassNotFoundException {
        TreeSet<String> ts=new TreeSet<>();
        Connection conn = DBConnection.getConnection();
        PreparedStatement psta = conn.prepareStatement("select "+newValue+"  from userinformation");
        ResultSet resultSet = psta.executeQuery();
        while (resultSet.next()){
            ts.add(resultSet.getString(1));
        }
        return ts;
    }

    public static ResultSet selectUserID(String userFields, String options) throws SQLException, ClassNotFoundException {
        Connection conn=DBConnection.getConnection();
        PreparedStatement psta=conn.prepareStatement("select userId from userinformation where "+userFields+"="+options);
        ResultSet rs=psta.executeQuery();
        return rs;
    }

    public static ResultSet selectPhysicalData(String string, String selectedFields) throws SQLException, ClassNotFoundException {
        Connection conn=DBConnection.getConnection();
        PreparedStatement psta=conn.prepareStatement("select "+selectedFields+" from physicalinformation where userId=?");
        psta.setString(1, string);
        ResultSet rs=psta.executeQuery();
        return rs;
    }

    public static ResultSet selectMentalData(String string, String selectedFields) throws SQLException, ClassNotFoundException {
        Connection conn=DBConnection.getConnection();
        PreparedStatement psta=conn.prepareStatement("select "+selectedFields+" from mentalinformation where userId=?");
        psta.setString(1, string);
        ResultSet rs=psta.executeQuery();
        return rs;
    }

    public static int SelectRow() throws SQLException, ClassNotFoundException {
        Connection coon=DBConnection.getConnection();
        PreparedStatement psta=coon.prepareStatement("select * from graph");
        ResultSet rs=psta.executeQuery();
        rs.last();
        return rs.getRow();
    }

    public static String selectPhysicalFieldType(String field) throws SQLException, ClassNotFoundException {
        Connection coon=DBConnection.getConnection();
        PreparedStatement psta=coon.prepareStatement("select data_type from information_schema.columns where table_name='physicalInformation'and column_name='"+field+"';");
        ResultSet rs=psta.executeQuery();
        String s=null;
        while (rs.next()){
            s=rs.getString(1);
        }
        return s;
    }
}
