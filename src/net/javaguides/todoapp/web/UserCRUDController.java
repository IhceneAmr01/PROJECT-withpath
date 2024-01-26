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
                    listUser1(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
 // USING THIS FOR DROPDOWN
    private void listUser1(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List<User> listUser1 = userDAO.selectAllUsers();
        request.setAttribute("listUser1", listUser1);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/list-users.jsp");
        dispatcher.forward(request, response);
    }
 // USING THIS FOR DROPDOWN
    private void listUser2(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List<User> listUser2 = userDAO.selectAllUsers();
        request.setAttribute("listUser", listUser2);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/todo-form.jsp");
        dispatcher.forward(request, response);
    }
// ALL THIS FOR LATER -----------------------------------------------------
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userDAO.selectUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/user-form.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);

    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        //String first_name = request.getParameter("first_name");
       // String last_name = request.getParameter("last_name");
       // String username = request.getParameter("username");
        User newUser = new User();
        userDAO.insertUser(newUser);
        response.sendRedirect("/users/list");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        //int id = Integer.parseInt(request.getParameter("id"));
        //String first_name = request.getParameter("first_name");
        //String last_name = request.getParameter("last_name");
        //String username = request.getParameter("username");

        User user = new User();
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
