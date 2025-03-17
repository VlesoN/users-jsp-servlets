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

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("add".equals(action)) {
            req.getRequestDispatcher("add.jsp").forward(req, resp);
        } else if ("edit".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            req.setAttribute("user", userService.getUserById(id));
            req.getRequestDispatcher("edit.jsp").forward(req, resp);
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            req.setAttribute("user", userService.getUserById(id));
            req.getRequestDispatcher("delete.jsp").forward(req, resp);
        } else {
            req.setAttribute("users", userService.getAllUsers());
            req.getRequestDispatcher("list.jsp").forward(req, resp);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String method = req.getParameter("_method");

        if ("PUT".equalsIgnoreCase(method)) {
            doPut(req, resp);
        } else if ("DELETE".equalsIgnoreCase(method)) {
            doDelete(req, resp);
        } else {
                userService.addUser(req.getParameter("username"), req.getParameter("password"), req.getParameter("email"));
            }

        resp.sendRedirect("users");
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)  {
        int id = Integer.parseInt(req.getParameter("id"));
        userService.updateUser(id, req.getParameter("username"), req.getParameter("password"), req.getParameter("email"));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)  {
        int id = Integer.parseInt(req.getParameter("id"));
        userService.deleteUser(id);
    }
}
