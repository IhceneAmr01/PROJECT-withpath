package net.javaguides.todoapp.dao;

import java.sql.SQLException;
import java.util.List;

import net.javaguides.todoapp.model.Todo;

public interface TodoDao {

    void insertTodo(Todo todo) throws SQLException;

    Todo selectTodo(long todoId);

    List<Todo> selectAllTodos();
    
    List<Todo> selectTodosByUsername(String username); // Changed method name to match Todo field

    boolean deleteTodo(long id) throws SQLException; // Changed the parameter type to match Todo ID

    boolean updateTodo(Todo todo) throws SQLException;
}
