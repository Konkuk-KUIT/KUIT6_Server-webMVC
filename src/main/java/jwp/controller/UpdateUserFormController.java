package jwp.controller;

import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateUserFormController implements Controller {
    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser != null &&
                sessionUser.getUserId().equals(req.getParameter("userId"))) {
            return "/user/updateForm.jsp";
        }
        return REDIRECT_PREFIX + "/";
    }
}
