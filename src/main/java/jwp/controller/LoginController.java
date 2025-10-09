package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginController implements Controller{

    @Override
    public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("userId");
        String pass = req.getParameter("password");

        MemoryUserRepository userRepository = MemoryUserRepository.getInstance();
        User findedUser = userRepository.findUserById(id);

        //실패 -> redirect
        if (findedUser == null || !findedUser.getPassword().equals(pass)) {
            return "/user/loginFailed.jsp";
        }
        HttpSession session = req.getSession();
        session.setAttribute("user", findedUser);

        return "redirect:/";
    }
}
