package jwp.controller;

import core.db.MemoryUserRepository;
import core.web.mvc.Controller;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class CreateUserController implements Controller {
    @Override
    public String handle(HttpServletRequest req, HttpServletResponse resp, Map<String, Object> model) throws Exception {
        User user = new User(req.getParameter("userid"), req.getParameter("password"), req.getParameter("name"), req.getParameter("email"));
        MemoryUserRepository.getInstance().addUser(user);
        System.out.println("user 회원가입 완료");
        return "redirect:/user/list";

    }
}
