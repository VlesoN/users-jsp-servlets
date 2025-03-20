package login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;
import user.User;

import java.io.IOException;
import java.util.Optional;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UserService userService = new UserService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Отправляем пользователя на страницу входа
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Optional<User> user = userService.authenticateUser(username, password);

        if (user.isPresent()) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("users"); // Перенаправление на список пользователей
        } else {
            req.setAttribute("errorMessage", "Неверный логин или пароль!");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}

