package jwp.controller;


import util.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateQuestionFormController implements Controller {
    @Override
    public String handle(HttpServletRequest req, HttpServletResponse resp){
        if (SessionUtil.isLogin(req)) {
            return "/qna/form.jsp";
        }
        return "redirect:/user/login.jsp";
    }
}
