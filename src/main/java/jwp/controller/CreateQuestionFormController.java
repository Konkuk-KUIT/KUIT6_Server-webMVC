package jwp.controller;

import core.web.mvc.Controller;
import jwp.dao.QuestionDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class CreateQuestionFormController implements Controller {
    @Override
    public String handle(HttpServletRequest req, HttpServletResponse resp, Map<String, Object> model) {
        HttpSession session = req.getSession(false);
        boolean loggedIn = session != null && session.getAttribute("user") != null;
        return loggedIn ? "/qna/form" : "redirect:/user/loginForm";
    }
}
