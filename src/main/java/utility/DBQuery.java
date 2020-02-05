package utility;

import config.Config;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBQuery {

    static String host;
    static String port;
    static String dbName;
    static String username;
    static String password;


    public static Connection getDBConnection() {
        host = Config.getProperty("db.host");
        port = Config.getProperty("db.port");
        dbName = Config.getProperty("db.dbName");
        username = Config.getProperty("db.username");
        password = Config.getProperty("db.password");


        //System.out.printf("\nhost %s\nport %s\ndbname %s\nusername %s\npassword %s\n",host,port,dbName,username,password);
        Connection connection = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC Driver probably not found");
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@" + host + ":" + port + ":" + dbName + "", username, password);
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
        return connection;
    }

    public static ArrayList<String> executeQuery(String query, String[] columns) {
        ArrayList<String> result = new ArrayList<String>();
        Statement statement = null;
        Connection con = getDBConnection();
        try {
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                for (String column : columns) {
                    result.add(rs.getString(column));
                }
            }
            rs.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ArrayList<HashMap<String, String>> executeQuery(String query) throws SQLException {
        Connection con = getDBConnection();
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = con.createStatement();
            rs = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<HashMap<String, String>> testList = (ArrayList) resultSetToArrayList(rs);
        con.close();
        rs.close();
        return testList;
    }

    public static List resultSetToArrayList(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        ArrayList list = new ArrayList();
        while (rs.next()) {
            HashMap row = new HashMap(columns);
            for (int i = 1; i <= columns; ++i) {
                row.put(md.getColumnName(i), rs.getObject(i));
            }
            list.add(row);
        }
        return list;
    }

    public static void executeUpdate(String query) throws SQLException {
        Connection con = getDBConnection();
        Statement statement = null;
        try {
            statement = con.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        con.close();
    }

}