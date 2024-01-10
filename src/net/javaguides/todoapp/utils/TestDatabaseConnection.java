package net.javaguides.todoapp.utils;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDatabaseConnection {

    public static void main(String[] args) {
        try {
            Connection connection = JDBCUtils.getConnection();
            if (connection != null) {
                System.out.println("Connected to the database!");
                connection.close();
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }
}
