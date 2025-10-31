package jwp.controller;

import jwp.dao.UserDao;
import jwp.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

public class ListUserController implements Controller {
    private final UserDao userDao = new UserDao();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        if ((User) req.getSession().getAttribute("user") == null) {
            return REDIRECT_PREFIX + "/user/login.jsp";
        }
        Collection<User> users = userDao.findAll();
        req.setAttribute("users", users);
        return "/user/list.jsp";
    }
}
