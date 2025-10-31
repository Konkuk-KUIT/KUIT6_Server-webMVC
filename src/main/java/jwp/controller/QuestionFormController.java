package jwp.controller;

import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuestionFormController implements Controller{
    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        if ((User) req.getSession().getAttribute("user") == null) {
            return REDIRECT_PREFIX + "/user/login.jsp";
        }
        return "/qna/form.jsp";
    }
}
