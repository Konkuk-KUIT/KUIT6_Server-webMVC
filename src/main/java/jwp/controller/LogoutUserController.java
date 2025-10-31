package jwp.controller;

import jwp.enums.HttpUrl;
import jwp.enums.ReqAttribute;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutUserController implements Controller {
    @Override
    public String service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute(ReqAttribute.USER.getValue());
        return HttpUrl.REDIRECT.getValue().concat(HttpUrl.ROOT.getValue());
    }
}
