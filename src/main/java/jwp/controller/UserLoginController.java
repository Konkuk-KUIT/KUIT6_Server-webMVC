package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserLoginController implements Controller {
    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        User user = MemoryUserRepository.getInstance().findUserById(req.getParameter("userId"));
        if (user != null && user.matchPassword(req.getParameter("password"))) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            return REDIRECT_PREFIX + "/";
        }
        return REDIRECT_PREFIX + "/user/loginFailed.jsp";
    }
}
