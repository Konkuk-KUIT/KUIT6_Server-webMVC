package jwp.controller;

import jwp.dao.UserDao;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ListUserController implements Controller {

    private final UserDao userDao = new UserDao();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");

        if (user != null) {
            List<User> users = userDao.findAll();
            req.setAttribute("users", users);
            return "/user/list.jsp";
        }

        return "redirect:/";
    }
}