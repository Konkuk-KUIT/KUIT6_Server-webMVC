package jwp.controller;

import jwp.dao.UserDao;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserLoginController implements Controller {
    private final UserDao userDao = new UserDao();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        User user = userDao.findByUserId(req.getParameter("userId"));
        if (user != null && user.matchPassword(req.getParameter("password"))) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            return REDIRECT_PREFIX + "/";
        }
        return REDIRECT_PREFIX + "/user/loginFailed.jsp";
    }
}
