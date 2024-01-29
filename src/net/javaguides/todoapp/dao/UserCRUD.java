package net.javaguides.todoapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.todoapp.model.User;
import net.javaguides.todoapp.utils.JDBCUtils;

public class UserCRUD {
    

	    private static final String INSERT_USERS_SQL = "INSERT INTO users (first_name, last_name, username, role, score) VALUES (?, ?, ?, ?, ?);";
	    private static final String SELECT_USER_BY_ID = "SELECT id, first_name, last_name, username, role, score FROM users WHERE id = ?";
	    private static final String SELECT_ALL_USERS = "SELECT id, first_name, last_name, username, role, score FROM users";
	    private static final String DELETE_USERS_SQL = "DELETE FROM users WHERE id = ?";
	    private static final String UPDATE_USERS_SQL = "UPDATE users SET first_name = ?, last_name = ?, username = ?, role = ?, score = ? WHERE id = ?";

    public UserCRUD() {}

    protected Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection connection = null;
        connection = JDBCUtils.getConnection();
        return connection;
    }
    //TO TEST LATER
    public void insertUser(User user) throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = JDBCUtils.getConnection();
        	PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setInt(5, user.getScore());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    //TO TEST LATER
    public User selectUser(int id) {
        User user = null;
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtils.getConnection();
             // Step 2: Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                // Use the existing 'id' variable declared at the class level
                int userId = rs.getInt("id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String username = rs.getString("username");
                String role = rs.getString("role");
                int score = rs.getInt("score");

                user = new User(userId, first_name, last_name, username, role, score);

            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }

    // NEED THIS FOR THE DROPDOWN 
    public List < User > selectAllUsers() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < User > users = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtils.getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                // Use the constructor directly to create a User object
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setUsername(rs.getString("username"));
                user.setRole(rs.getString("role"));
                user.setScore(rs.getInt("score"));
                users.add(user);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }
    //TO TEST LATER------------------------------------
    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = JDBCUtils.getConnection(); 
        	PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = JDBCUtils.getConnection();
        	PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getUsername());
            //statement.setInt(4, (int) user.getId());
            statement.setString(4, user.getRole());
            statement.setInt(5, user.getScore());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}