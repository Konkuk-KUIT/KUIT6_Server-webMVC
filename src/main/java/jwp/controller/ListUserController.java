package jwp.controller;

import core.db.MemoryUserRepository;
import core.mvc.Controller;
import jwp.dao.UserDao;
import jwp.model.User;
import jwp.util.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ListUserController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        UserDao dao = new UserDao();
        List<User> users = dao.findAll();
        if (UserSessionUtils.isLogined(session)) {
            users = dao.findAll();
//            req.setAttribute("users", MemoryUserRepository.getInstance().findAll());
            req.setAttribute("users", users);
            return "/user/list.jsp";
        }
        return "redirect:/user/loginForm";
    }
}