package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateUserController implements Controller {

    private final MemoryUserRepository userRepository = MemoryUserRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/";
        }
        User sessionUser = (User) session.getAttribute("user");
        String userId = request.getParameter("userId");

        if (sessionUser == null || !sessionUser.getUserId().equals(userId)) {
            return "redirect:/";
        }

        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        User updatedUser = new User(userId, password, name, email);
        userRepository.changeUserInfo(updatedUser);
        session.setAttribute("user", updatedUser);

        return "redirect:/user/list";
    }
}
