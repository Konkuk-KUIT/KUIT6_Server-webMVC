package jwp.controller;

import core.web.mvc.Controller;
import jwp.dao.QuestionDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class CreateQuestionFormController implements Controller {
    @Override
    public String handle(HttpServletRequest req,
                         HttpServletResponse resp,
                         Map<String, Object> model) throws Exception {

        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            req.getRequestDispatcher("/qna/form.jsp").forward(req, resp);
            return null;
        }

        resp.sendRedirect("/user/loginForm");
        return null;
    }
}
