package net.javaguides.todoapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.todoapp.model.Todo;
import net.javaguides.todoapp.model.User;
import net.javaguides.todoapp.dao.TodoDao;
import net.javaguides.todoapp.utils.JDBCUtils;

public class TodoDaoImpl implements TodoDao {

    private static final String INSERT_TODOS_SQL = "INSERT INTO todos (title, user_id, username, description, target_date, status) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_TODO_BY_ID = "SELECT id, title, user_id, username, description, target_date, status FROM todos WHERE id = ?";
    private static final String SELECT_ALL_TODOS = "SELECT id, title, user_id, username, description, target_date, status FROM todos";
    private static final String SELECT_TODOS_BY_USERNAME = "SELECT id, title, user_id, username, description, target_date, status FROM todos WHERE username = ?";
    private static final String DELETE_TODO_BY_ID = "DELETE FROM todos WHERE id = ?";
    private static final String UPDATE_TODO = "UPDATE todos SET title = ?, user_id = ?, username = ?, description = ?, target_date = ?, status = ? WHERE id = ?";
    private static final String SELECT_USER_BY_ID = "SELECT id, username FROM users WHERE id = ?";

    @Override
    public void insertTodo(Todo todo) throws SQLException {
    	
    	
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(INSERT_TODOS_SQL)) {
            insertStatement.setString(1, todo.getTitle());
            insertStatement.setLong(2, todo.getUserId());
            insertStatement.setString(3, todo.getUsername());
            insertStatement.setString(4, todo.getDescription());
            insertStatement.setDate(5, JDBCUtils.getSQLDate(todo.getTargetDate()));
            insertStatement.setString(6, todo.getStatus());

            insertStatement.executeUpdate();
        }
    }

    @Override
    public Todo selectTodo(long todoId) {
        Todo todo = null;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TODO_BY_ID)) {
            preparedStatement.setLong(1, todoId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                String title = rs.getString("title");
                Long userId = rs.getLong("user_id");
                String username = rs.getString("username");
                String description = rs.getString("description");
                LocalDate targetDate = rs.getDate("target_date").toLocalDate();
                String status = rs.getString("status");
                
                User user = fetchUserDetails(userId);

                
                todo = new Todo(id, title, userId, username, description, targetDate, status);
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return todo;
    }
    
 // Method to fetch user details from the Users table based on the userId
    private User fetchUserDetails(Long userId) {
        User user = null;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {

            preparedStatement.setLong(1, userId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                long id = rs.getLong("id");
                String username = rs.getString("username");
                // Retrieve other user details as needed
                user = new User(); // Initialize the User object here
                user.setIdAndUsername(id, username); // Set retrieved details to the User object
            } else {
                // If user with provided userId is not found, create a default user object
                user = createDefaultUser(); // Create a method to generate a default user
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return user;
    }

    // Method to create a default user object
    private User createDefaultUser() {
        User defaultUser = new User();
        defaultUser.setId(1); // Set a default ID or any other identifier
        defaultUser.setUsername("Default User");
        // Set other default values as needed
        return defaultUser;
    }


    @Override
    public List<Todo> selectAllTodos() {
        List<Todo> todos = new ArrayList<>();
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TODOS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String title = rs.getString("title");
                Long userId = rs.getLong("user_id");
                String username = rs.getString("username");
                String description = rs.getString("description");
                LocalDate targetDate = rs.getDate("target_date").toLocalDate();
                String status = rs.getString("status");
                
                todos.add(new Todo(id, title, userId, username, description, targetDate, status));
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return todos;
    }

    @Override
    public boolean deleteTodo(long id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_TODO_BY_ID)) {
            statement.setLong(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean updateTodo(Todo todo) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_TODO)) {
            statement.setString(1, todo.getTitle());
            statement.setLong(2, todo.getUserId());
            statement.setString(3, todo.getUsername());
            statement.setString(4, todo.getDescription());
            statement.setDate(5, JDBCUtils.getSQLDate(todo.getTargetDate()));
            statement.setString(6, todo.getStatus());
            statement.setLong(7, todo.getId());
            rowUpdated = statement.executeUpdate() > 0;
        
        }
        return rowUpdated;
    }

    @Override
    public List<Todo> selectTodosByUsername(String username) {
        List<Todo> todos = new ArrayList<>();
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TODOS_BY_USERNAME)) {
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String title = rs.getString("title");
                Long userId = rs.getLong("user_id");
                String user = rs.getString("username");
                String description = rs.getString("description");
                LocalDate targetDate = null;
                java.sql.Date sqlDate = rs.getDate("target_date");
                if (sqlDate != null) {
                    targetDate = sqlDate.toLocalDate();
                }
                String status = rs.getString("status");
                todos.add(new Todo(id, title, userId, user, description, targetDate, status));
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return todos;
    }
}
