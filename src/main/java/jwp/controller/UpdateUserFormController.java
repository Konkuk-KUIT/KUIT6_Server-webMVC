package jwp.controller;

import core.web.SessionConst;
import core.web.mvc.Controller;
import jwp.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class UpdateUserFormController implements Controller {
    @Override
    public String handle(HttpServletRequest req, HttpServletResponse resp, Map<String, Object> model) throws Exception {
       String requestedId = req.getParameter("userId");

       HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute(SessionConst.USER_SESSION_KEY) == null) {
            return "redirect:/user/login.html";
        }


        User loginUser = (User)session.getAttribute(SessionConst.USER_SESSION_KEY);

        if (requestedId == null || !requestedId.equals(loginUser.getUserId())) {
            return "redirect:/";
        }

        model.put("user", loginUser);
        return "/user/updateForm";

    }
}
