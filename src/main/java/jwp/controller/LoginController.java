package jwp.controller;

import core.db.MemoryUserRepository;
import core.web.SessionConst;
import core.web.mvc.Controller;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class LoginController implements Controller {
    private final MemoryUserRepository userRepository = MemoryUserRepository.getInstance();

    @Override
    public String handle(HttpServletRequest req, HttpServletResponse resp, Map<String, Object> model) throws Exception {
        if (!"POST".equalsIgnoreCase(req.getMethod())) {
            return "redirect:/user/login.html";
        }

        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        User user = userRepository.findUserById(userId);
        boolean ok = (user != null) && user.matchPassword(password);

        if (ok) {
            HttpSession session = req.getSession();
            session.setAttribute(SessionConst.USER_SESSION_KEY, user);
            return "redirect:/";
        } else {
            return "/user/loginFailed";
        }
    }
}
