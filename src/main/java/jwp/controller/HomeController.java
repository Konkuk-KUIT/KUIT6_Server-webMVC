package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class HomeController implements Controller {
    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<Question> questions = questionDao.findAll();
        req.setAttribute("questions", questions);
        return "/home.jsp";
    }
}