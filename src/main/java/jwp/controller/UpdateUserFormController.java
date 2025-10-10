package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateUserFormController implements Controller {
    @Override
    public String executePost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User currentUser = MemoryUserRepository.getInstance().findUserById(req.getParameter("userId"));
        currentUser.update(new User("",
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email")));

        return "redirect:/user/list";
    }
}
