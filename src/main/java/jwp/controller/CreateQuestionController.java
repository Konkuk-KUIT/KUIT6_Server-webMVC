package jwp.controller;

import core.web.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class CreateQuestionController implements Controller {
    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public String handle(HttpServletRequest req, HttpServletResponse resp, Map<String, Object> model) throws Exception {
        HttpSession session = req.getSession(false);
        User loginUser = (session == null) ? null : (User) session.getAttribute("user");
        if (loginUser == null) {
            resp.sendRedirect("/user/loginForm");
            return null;
        }

        String title = req.getParameter("title");
        String contents = req.getParameter("contents");
        String writer = loginUser.getUserId();

        Question saved = questionDao.insert(writer, title, contents);


        resp.sendRedirect("/home");

        return null;
    }
}
