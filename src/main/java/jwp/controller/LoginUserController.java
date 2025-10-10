package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.enums.HttpUrl;
import jwp.enums.JspUrl;
import jwp.enums.UserParameter;
import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginUserController implements Controller {
    @Override
    public String service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = MemoryUserRepository.getInstance().findUserById(req.getParameter(UserParameter.USER_ID.getValue()));
        if (!isLogin(req, user)) {
           return JspUrl.LOGIN_FAIL.getValue();
        }

        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        return HttpUrl.REDIRECT.getValue().concat(HttpUrl.ROOT.getValue());
    }

    private static boolean isLogin(HttpServletRequest req, User user) {
        return user != null && user.matchPassword(req.getParameter(UserParameter.PASSWORD.getValue()));
    }
}
