package com.goit.module7.utils.DB;

/**
 * Created by Администратор on 18.01.2016.
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GRUD {

    Connection connection;

    public GRUD(Connection connection) {
        this.connection = connection;
    }

    public void createTable(String name) {
        try {
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE " + name + " (id IDENTITY , value VARCHAR(255))";
            statement.executeUpdate(sql);
        } catch (SQLException e) {

        }
    }

    public void deleteTable(String name) {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM " + name;
            statement.executeUpdate(sql);
        } catch (SQLException e) {

        }
    }

    public void insert(String name, double result) {
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO " + name + " (value) VALUES('" + result + "')";
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void select(String name) {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT id, value FROM " + name;
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                System.out.println("Результат: " + resultSet.getInt(1) + " = "
                        + resultSet.getString(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}