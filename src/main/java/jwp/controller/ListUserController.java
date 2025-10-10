package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

public class ListUserController  implements Controller {
    @Override
    public String process(HttpServletRequest rq, HttpServletResponse resp) throws Exception {

        HttpSession session = rq.getSession();
        Object user = session.getAttribute("user");
        if (user != null) {
            Collection<User> users = MemoryUserRepository.getInstance().findAll();
            rq.setAttribute("users",users); // servlet -> jsp 데이터 전달
            return "/user/list.jsp";
        }

        return "redirect:/";
    }

}
