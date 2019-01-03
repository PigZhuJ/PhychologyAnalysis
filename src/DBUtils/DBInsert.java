package DBUtils;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class DBInsert {

    public static void insertUser(String s) throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getConnection();
        String str="";
        String[] ss=s.split(",");
        for (int i = 0; i < ss.length; i++) {
            str+=ss[i]+",";
        }
        str=str.substring(0,str.length()-1);
        System.out.println(str);
        String sql="INSERT into userinformation values ("+str+");";
        System.out.println(sql);
        Statement statement=connection.createStatement();
        statement.execute(sql);
    }
    public static void insertMentabl(String s,String  field) throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getConnection();
        String[] strArr1=s.split(",");//传进来的字段
        String[] strArr2=field.split(",");
        String indeed="";
        for (int i = 0; i < strArr2.length; i++) {
            if(DBSelect.selectPhysicalFieldType(strArr2[i]).equals("varchar")){
                indeed+="'"+strArr1[i]+"',";
            }else if(DBSelect.selectPhysicalFieldType(strArr2[i]).equals("varchar")){
                indeed+=strArr1[i]+",";
            }
        }
        indeed=indeed.substring(0,indeed.length()-1);
        String sql="INSERT into mentalinformation ("+field+") values ("+indeed+")";
        System.out.println(sql);
        Statement statement=connection.createStatement();
        statement.execute(sql);
    }
    public static void insertPhysical(String s) throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getConnection();
        String str="";
        String[] ss=s.split(",");
        for (int i = 0; i < ss.length; i++) {
            str+=ss[i]+",";
        }
        str=str.substring(0,str.length()-1);
        System.out.println(str);
        String sql="INSERT into physicalinformation values ("+str+")";
        System.out.println(sql);
        Statement statement=connection.createStatement();
        statement.execute(sql);
    }



    public static void insertGraph(int i, String text, String value, String value1, String value2) throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getConnection();
        String s="";
        s=s+i+",'"+text+"','"+value+"','"+value1+"','"+value2+"'";
        String sql="INSERT into graph values ("+s+")";
        System.out.println(sql);
        Statement statement=connection.createStatement();
        statement.execute(sql);
    }
}
