package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.enums.HttpUrl;
import jwp.enums.JspUrl;
import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class ListUserController implements Controller {
    @Override
    public String service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!isUserWithSession(req)) {
            return HttpUrl.REDIRECT.getValue().concat(HttpUrl.ROOT.getValue());
        }
        Collection<User> users = MemoryUserRepository.getInstance().findAll();
        req.setAttribute("users", users);
       return JspUrl.LIST.getValue();
    }

    private static boolean isUserWithSession(HttpServletRequest req) {
        return req.getSession().getAttribute("user") != null;
    }
}
