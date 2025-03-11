package controller;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;
import java.io.IOException;
import jakarta.servlet.ServletException;


@WebServlet("/users")
public class UserServlet extends HttpServlet {
    private UserService userService = new UserService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            request.getRequestDispatcher("add.jsp").forward(request, response);
        } else if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("user", userService.getUserById(id));
            request.getRequestDispatcher("edit.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("user", userService.getUserById(id));
            request.getRequestDispatcher("delete.jsp").forward(request, response);
        } else {
            request.setAttribute("users", userService.getAllUsers());
            request.getRequestDispatcher("list.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            userService.addUser(request.getParameter("username"), request.getParameter("password"), request.getParameter("email"));
        } else if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            userService.updateUser(id, request.getParameter("username"), request.getParameter("password"), request.getParameter("email"));
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            userService.deleteUser(id);
        }

        response.sendRedirect("users");
    }

}
