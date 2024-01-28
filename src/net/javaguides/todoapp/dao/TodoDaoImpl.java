package net.javaguides.todoapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.todoapp.model.Todo;
import net.javaguides.todoapp.utils.JDBCUtils;

public class TodoDaoImpl implements TodoDao {

    private static final String INSERT_TODOS_SQL = "INSERT INTO todos (title, description, target_date, status, user_id) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_TODO_BY_ID = "SELECT id, title, description, target_date, status, user_id FROM todos WHERE id = ?";
    private static final String SELECT_ALL_TODOS = "SELECT id, title, description, target_date, status, user_id FROM todos";
    private static final String DELETE_TODO_BY_ID = "DELETE FROM todos WHERE id = ?";
    private static final String UPDATE_TODO = "UPDATE todos SET title = ?, description = ?, target_date = ?, status = ?, user_id = ? WHERE id = ?";
   // private static final String SELECT_USER_BY_ID = "SELECT id, username FROM users WHERE id = ?";

    @Override
    public void insertTodo(Todo todo) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(INSERT_TODOS_SQL)) {
            insertStatement.setString(1, todo.getTitle());
            insertStatement.setString(2, todo.getDescription()); // Corrected index
            insertStatement.setDate(3, JDBCUtils.getSQLDate(todo.getTargetDate())); // Corrected index
            insertStatement.setString(4, todo.getStatus()); // Corrected index
            insertStatement.setInt(5, todo.getUserId());
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
                String description = rs.getString("description");
                LocalDate targetDate = rs.getDate("target_date").toLocalDate();
                String status = rs.getString("status");
                
                int user_id = rs.getInt("user_id");
                

                
                todo = new Todo(id, title, description, targetDate, status, user_id);
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return todo;
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
                String description = rs.getString("description");
                LocalDate targetDate = rs.getDate("target_date").toLocalDate();
                String status = rs.getString("status");
                
                int user_id = rs.getInt("user_id");

                
                todos.add(new Todo(id, title, description, targetDate, status, user_id));
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
            statement.setString(2, todo.getDescription()); // Corrected index
            statement.setDate(3, JDBCUtils.getSQLDate(todo.getTargetDate())); // Corrected index
            statement.setString(4, todo.getStatus()); // Corrected index
            statement.setLong(5, todo.getId()); // Corrected index
            statement.setInt(6, todo.getUserId()); // Corrected index

            rowUpdated = statement.executeUpdate() > 0;
        
        }
        return rowUpdated;
    }

	
   
    
    
	}


