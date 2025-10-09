package jwp.controller;

import core.db.MemoryUserRepository;
import core.web.SessionConst;
import core.web.mvc.Controller;
import jwp.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Map;

public class ListUserController implements Controller {
    private final MemoryUserRepository memoryUserRepository = MemoryUserRepository.getInstance();

    @Override
    public String handle(HttpServletRequest req, HttpServletResponse resp, Map<String, Object> model) throws Exception {
        HttpSession session = req.getSession(false);
        User loginUser = (User) session.getAttribute(SessionConst.USER_SESSION_KEY);

        req.setAttribute("loginUser", loginUser);
        Collection<User> users = memoryUserRepository.findAll();
        req.setAttribute("users", users);

        return "/user/list";
    }
}
