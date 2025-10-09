package jwp.controller;
import core.web.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class LogoutController implements Controller {
    @Override
    public String handle(HttpServletRequest req, HttpServletResponse resp, Map<String, Object> model) throws Exception {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.removeAttribute("user");
        }
        return "redirect:/";
    }

}
