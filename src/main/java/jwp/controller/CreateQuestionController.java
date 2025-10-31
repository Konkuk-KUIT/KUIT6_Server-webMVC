package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateQuestionController implements Controller{
    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        if ((User) req.getSession().getAttribute("user") == null) {
            return REDIRECT_PREFIX + "/user/login.jsp";
        }
        questionDao.insert(req.getParameter("writer"), req.getParameter("title"), req.getParameter("contents"));
        return REDIRECT_PREFIX + "/";
    }
}
