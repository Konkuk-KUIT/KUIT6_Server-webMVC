package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/user/update")
public class UpdateUserFormController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = MemoryUserRepository.getInstance().findUserById(req.getParameter("userId"));
        currentUser.update(new User("",
            req.getParameter("password"),
            req.getParameter("name"),
            req.getParameter("email")));

        resp.sendRedirect("/user/list");
    }
}
