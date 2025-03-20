package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;

import java.io.IOException;
import java.util.Optional;

import jakarta.servlet.ServletException;
import user.User;


@WebServlet("/users")
public class UserServlet extends HttpServlet {

    private final UserService userService = new UserService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Проверяем, авторизован ли пользователь
        if (req.getSession().getAttribute("user") == null) {
            resp.sendRedirect("login"); // Если нет, редиректим на страницу входа
            return;
        }

        String action = req.getParameter("action");

        if ("add".equals(action)) {
            req.getRequestDispatcher("add.jsp").forward(req, resp);
        } else if ("edit".equals(action)) {

            int id = Integer.parseInt(req.getParameter("id"));
            Optional<User> user = userService.getUserById(id);

            if (user.isPresent()) {
                req.setAttribute("user", user.get());
                req.getRequestDispatcher("edit.jsp").forward(req, resp);
            }
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            Optional<User> user = userService.getUserById(id);

            if (user.isPresent()) {
                req.setAttribute("user", user.get());
                req.getRequestDispatcher("delete.jsp").forward(req, resp);
            }
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
            userService.addUser(
                    req.getParameter("username"),
                    req.getParameter("password"),
                    req.getParameter("email")
            );
        }
        resp.sendRedirect("users");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        userService.updateUser(
                Integer.parseInt(req.getParameter("id")),
                req.getParameter("username"),
                req.getParameter("password"),
                req.getParameter("email")
        );
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        userService.deleteUser(Integer.parseInt(req.getParameter("id")));
    }
}
