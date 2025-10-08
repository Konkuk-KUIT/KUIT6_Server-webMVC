package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/login")
public class LoginUserController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = MemoryUserRepository.getInstance().findUserById(req.getParameter("userId"));
        if (user == null || !user.matchPassword(req.getParameter("password"))) {
            RequestDispatcher rd = req.getRequestDispatcher("/user/loginFailed.jsp");
            rd.forward(req, resp);
        }

        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        resp.sendRedirect("/");
    }
}
