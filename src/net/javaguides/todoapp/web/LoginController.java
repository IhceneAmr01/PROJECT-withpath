package net.javaguides.todoapp.web;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.javaguides.todoapp.dao.LoginDao;
import net.javaguides.todoapp.model.LoginBean;


@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginDao loginDao;

    public void init() {
        loginDao = new LoginDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        authenticate(request, response);
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        LoginBean loginBean = new LoginBean();
        loginBean.setUsername(username);
        loginBean.setPassword(password);
        

        try {
            if (loginDao.validate(loginBean)) {
            	if (loginBean.getUsername().equals("admin") && loginBean.getPassword().equals("admin")) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/list-user.jsp");
                    dispatcher.forward(request, response);
            	}
            	else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/todo-list.jsp");
                dispatcher.forward(request, response);
                }}
             else {
                HttpSession session = request.getSession();
                //session.setAttribute("user", username);
               // response.sendRedirect("/WEB-INF/login.jsp");
            }
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
