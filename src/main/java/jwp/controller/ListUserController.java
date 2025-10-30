package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.dao.UserDao;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

public class ListUserController implements Controller {
    @Override
    public String service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        Collection<User> users = userDao.findAll();
        req.setAttribute("users", users);
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("user");
        if(currentUser == null) {
            return "redirect:/";
        }
        else{
            return "/user/list";
        }

    }
}
