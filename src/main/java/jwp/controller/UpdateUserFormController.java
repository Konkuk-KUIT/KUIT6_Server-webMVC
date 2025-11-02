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

public class UpdateUserFormController implements Controller {

    private final MemoryUserRepository userRepository = MemoryUserRepository.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        HttpSession session = req.getSession(false);
        if (session == null) { // 로그인한 적 없는 사용자가 접근 시
            return "redirect:/";
        }
        User sessionUser = (User) session.getAttribute("user");

        if (sessionUser != null && sessionUser.getUserId().equals(userId)) {
            User user = userRepository.findUserById(userId);
            req.setAttribute("user", user);
            return "/user/updateForm";
        } else {
            return "redirect:/";
        }
    }
}
