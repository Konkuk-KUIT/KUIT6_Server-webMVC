package jwp.controller;

import core.db.MemoryUserRepository;
import core.web.SessionConst;
import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@WebServlet("user/list")
public class ListUserController extends HttpServlet {
    private final MemoryUserRepository memoryUserRepository = MemoryUserRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User loginUser = (User) session.getAttribute(SessionConst.USER_SESSION_KEY);

        req.setAttribute("loginUser", loginUser);

        Collection<User> users = memoryUserRepository.findAll();
        req.setAttribute("users", users);

        req.getRequestDispatcher("/user/list.jsp").forward(req, resp);
    }
}
