package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.dao.UserDao;
import jwp.enums.HttpUrl;
import jwp.enums.JspUrl;
import jwp.enums.ReqAttribute;
import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class ListUserController implements Controller {
    @Override
    public String service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        if(!isUserWithSession(req)) {
            return HttpUrl.REDIRECT.getValue().concat(HttpUrl.ROOT.getValue());
        }
        List<User> users = UserDao.getInstance().findAll();
        req.setAttribute(ReqAttribute.USERS.getValue(), users);
       return JspUrl.LIST.getValue();
    }

    private static boolean isUserWithSession(HttpServletRequest req) {
        return req.getSession().getAttribute(ReqAttribute.USER.getValue()) != null;
    }
}
