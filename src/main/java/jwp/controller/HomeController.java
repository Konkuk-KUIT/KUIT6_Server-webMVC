package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;

public class HomeController implements Controller {
    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        QuestionDao questionDao = new QuestionDao();
        Collection<Question> questions = questionDao.findAll();
        req.setAttribute("questions", questions);
        return "/home.jsp";
    }
}
