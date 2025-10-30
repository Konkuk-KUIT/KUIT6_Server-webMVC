package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowController implements Controller {
    @Override
    public String service(HttpServletRequest req, HttpServletResponse resp) {

        QuestionDao questionDao = new QuestionDao();
        String questionId = req.getParameter("questionId");
        Question question = questionDao.findById((Long.parseLong(questionId)));
        req.setAttribute("question", question);

        return "/qna/show";
    }
}
