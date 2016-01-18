package com.goit.module7.utils.DB;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Администратор on 18.01.2016.
 */
public class DB {
//
//    private static String SERVER= "213.174.1.43";
//    private static String PORT = "3306";
//    private static String DB_NAME = "test";
//    private static String USER = "test";
//    private static String PASSWORD = "12345";
//    private static String URL = "jdbc:mysql://" + SERVER + ":" + PORT + "/" + DB_NAME;

    private static Connection connection = null;

    private static boolean loadDriver() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException e) {
            System.err.println("Драйвер не найдений!!!");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static Connection getConnection() {

        try {
            String path = "mypath/";
            String dbname = "mydb";
            String connectionString = "jdbc:hsqldb:file:"+path+dbname;
            String login = "joe";
            String password = "password";
            connection = DriverManager.getConnection(connectionString, login, password);

        } catch (SQLException e) {
            System.err.println("Зєднання не створено!!!");
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() {

        Statement statement;
        try {
            statement = connection.createStatement();
            String sql = "SHUTDOWN";
            statement.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
