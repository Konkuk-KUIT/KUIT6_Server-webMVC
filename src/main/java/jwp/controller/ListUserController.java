package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

public class ListUserController implements Controller {
    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        if ((User) req.getSession().getAttribute("user") == null) {
            return REDIRECT_PREFIX + "/user/login";
        }
        Collection<User> users = MemoryUserRepository.getInstance().findAll();
        req.setAttribute("users", users);
        return "/user/list.jsp";
    }
}
