package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.dao.UserDao;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserController implements Controller {
    private final UserDao userDao = new UserDao();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        User user = userDao.findByUserId(req.getParameter("userId"));
        if (user == null) return REDIRECT_PREFIX + "/user/login";
        user.update(new User(user.getUserId(), req.getParameter("password"),
                req.getParameter("name"), req.getParameter("email")));
        userDao.update(user);
        return REDIRECT_PREFIX + "/user/list";
    }
}
