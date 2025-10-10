package jwp.controller;

import core.db.MemoryUserRepository;
import core.mvc.Controller;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * 사용자 목록을 보여주는 컨트롤러
 */
public class ForwardListUserController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Collection<User> users = MemoryUserRepository.getInstance().findAll();
        request.setAttribute("users", users);

        return "/user/list";
    }
}
