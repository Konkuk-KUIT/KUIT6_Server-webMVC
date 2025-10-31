package jwp.controller;

import jwp.enums.HttpUrl;
import jwp.enums.JspUrl;
import jwp.enums.ReqAttribute;
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

public class UpdateFormController implements Controller {
    @Override
    public String service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute(ReqAttribute.USER.getValue());
        if(isSameUserWithSession(req, user))
            return HttpUrl.REDIRECT.getValue().concat(HttpUrl.ROOT.getValue());
        return JspUrl.UPDATE_FORM.getValue();
    }

    private static boolean isSameUserWithSession(HttpServletRequest req, User user) {
        return !user.isSameUser(req.getParameter(UserParameter.USER_ID.getValue()));
    }
}
