package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserController implements Controller {
    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        MemoryUserRepository memoryUserRepository = MemoryUserRepository.getInstance();
        User user = memoryUserRepository.findUserById(req.getParameter("userId"));
        if (user == null) return REDIRECT_PREFIX + "/user/login";

        user.update(new User(user.getUserId(), req.getParameter("password"),
                req.getParameter("name"), req.getParameter("email")));
        memoryUserRepository.changeUserInfo(user);
        return REDIRECT_PREFIX + "/user/list";
    }
}
