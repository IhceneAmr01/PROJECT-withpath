package net.javaguides.todoapp.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import net.javaguides.todoapp.dao.UserCRUD;
import net.javaguides.todoapp.model.User;



@WebServlet("/users/*")
public class UserCRUDController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserCRUD userDAO;

    public void init() {
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

         System.out.println("Action: " + action);  // Print the action for debugging

        try {
            switch (action) {
                case "users/new":
                    showNewForm(request, response);
                    break;
                case "users/insert":
                    insertUser(request, response);
                    break;
                case "users/delete":
                    deleteUser(request, response);
                    break;
                case "users/edit":
                    showEditForm(request, response);
                    break;
                case "users/update":
                    updateUser(request, response);
                    break;
                default:
                    listUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
 
    private void listUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List<User> listUser = userDAO.selectAllUsers();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/list-users.jsp");
        dispatcher.forward(request, response);
    }
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/form-users.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userDAO.selectUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/form-users.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);

    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String username = request.getParameter("username");
        
		User newUser = new User(id, first_name, last_name, username);
        userDAO.insertUser(newUser);
        response.sendRedirect("/users/list");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
    	int id = Integer.parseInt(request.getParameter("id"));

        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String username = request.getParameter("username");
        
		User user = new User(id, first_name, last_name, username);
        userDAO.updateUser(user);
        response.sendRedirect("/users/list");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDAO.deleteUser(id);
        response.sendRedirect("/users/list");

    }
}
