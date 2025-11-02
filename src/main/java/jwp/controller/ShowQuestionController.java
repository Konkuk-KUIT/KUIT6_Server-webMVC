package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowQuestionController implements Controller {
    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        long questionId = Long.parseLong(req.getParameter("questionId"));
        Question question = questionDao.findByQuestionId(questionId);
        req.setAttribute("question", question);
        return "/qna/show.jsp";
    }
}