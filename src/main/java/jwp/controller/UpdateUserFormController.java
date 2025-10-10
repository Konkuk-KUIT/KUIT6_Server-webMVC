package jwp.controller;

import jwp.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateUserFormController implements Controller {
    @Override
    public String process(HttpServletRequest rq, HttpServletResponse resp) throws Exception {
        HttpSession session = rq.getSession();
        User loginUser = (User) session.getAttribute("user");
        String userId = rq.getParameter("userId");

        if (loginUser != null && userId.equals(loginUser.getUserId())){
            rq.setAttribute("user", loginUser);
            return "/user/updateForm.jsp";
        }
        return "redirect:/";

    }
}
