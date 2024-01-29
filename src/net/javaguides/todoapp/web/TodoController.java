package net.javaguides.todoapp.web;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.javaguides.todoapp.dao.TodoDao;
import net.javaguides.todoapp.dao.TodoDaoImpl;
import net.javaguides.todoapp.dao.UserCRUD;
import net.javaguides.todoapp.model.Todo;
import net.javaguides.todoapp.model.User;

@WebServlet("/todo/*")
public class TodoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TodoDao todoDao;
    private UserCRUD userDAO;

    public void init() {
        todoDao = new TodoDaoImpl();
        userDAO = new UserCRUD();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String servletPath = request.getServletPath();
        final String pathInfo = request.getPathInfo();
        final String action = servletPath + (pathInfo != null ? pathInfo : "");

        HttpSession session = request.getSession(false);

        // Check if the user is logged in
        boolean loggedIn = session != null && session.getAttribute("loggedIn") != null && (boolean) session.getAttribute("loggedIn");

        try {
            switch (action) {
                case "/todo/new":
                    showNewForm(request, response);
                    break;
                case "/todo/insert":
                    insertTodo(request, response);
                    break;
                case "/todo/delete":
                    deleteTodo(request, response);
                    break;
                case "/todo/edit":
                    showEditForm(request, response);
                    break;
                case "/todo/update":
                    updateTodo(request, response);
                    break;
                case "/todo/logout":
                    if (loggedIn) {
                        // Logout action
                        if (session != null) {
                            session.invalidate();
                            response.sendRedirect(request.getContextPath() + "/login");
                            return;
                        }
                    }
                    break;
                default:
                    // If not logged in, redirect to login page
                    if (!loggedIn) {
                        response.sendRedirect(request.getContextPath() + "/login");
                        return;
                    }
                    listTodos(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }


    private void listTodos(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        final List<Todo> listTodo = todoDao.selectAllTodos();
        request.setAttribute("listTodo", listTodo);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/todo-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	final List<User> listUser = userDAO.selectAllUsers();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/todo-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        final long id = Long.parseLong(request.getParameter("id"));
        Todo existingTodo = todoDao.selectTodo(id);
        final List<User> listUser = userDAO.selectAllUsers();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/todo-form.jsp");
        request.setAttribute("todo", existingTodo);
        dispatcher.forward(request, response);
    }

    private void insertTodo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
        final String title = request.getParameter("title");
        final String description = request.getParameter("description");
        final LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"));
        final String status = request.getParameter("status");
        final List<User> listUser = userDAO.selectAllUsers();

        int user_id = -1; // Set a default value, or handle the case where listUser is empty
        if (!listUser.isEmpty()) {
            user_id = (int) listUser.get(0).getId(); // Assuming User class has a getId() method
        }

        Todo newTodo = new Todo(title, description, targetDate, status, user_id);
        todoDao.insertTodo(newTodo);
        

        //Todo newTodo = new Todo(title, userId, username, description, targetLocalDate, false);
        //todoDao.insertTodo(newTodo);
        response.sendRedirect("todo/list");
    }

    private void updateTodo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        final long id = Long.parseLong(request.getParameter("id"));
        final String title = request.getParameter("title");
        
        final String description = request.getParameter("description");
        final String targetDate = request.getParameter("targetDate");
        final LocalDate targetLocalDate = LocalDate.parse(targetDate);
        final String status = request.getParameter("status");
        final List<User> listUser = userDAO.selectAllUsers();

        // Use the user_id of the first user from the list
        int user_id = -1; // Set a default value, or handle the case where listUser is empty
        if (!listUser.isEmpty()) {
            user_id = (int) listUser.get(0).getId(); // Assuming User class has a getId() method
        }


        Todo todo = new Todo(id, title, description, targetLocalDate, status, user_id);
        todoDao.updateTodo(todo);
        response.sendRedirect("todo/list");
    }

    private void deleteTodo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        final long id = Long.parseLong(request.getParameter("id"));
        todoDao.deleteTodo(id);
        response.sendRedirect("todo/list");
    }
}
