package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/update")
public class UpdateUserController extends HttpServlet {
    private final MemoryUserRepository memoryUserRepository = MemoryUserRepository.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session == null || session.getAttribute("user") == null){
            resp.sendRedirect("/user/login.html");
            return;
        }
        User loginUser = (User)session.getAttribute("user");

        String userId = req.getParameter("userId");
        if(userId==null||!userId.equals(loginUser.getUserId())){
            resp.sendRedirect("/");
            return;
        }

        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        User updateUser = new User(userId,password,name,email);
        memoryUserRepository.changeUserInfo(updateUser);

        session.setAttribute("user",updateUser);

        resp.sendRedirect("/user/list");
    }
}
