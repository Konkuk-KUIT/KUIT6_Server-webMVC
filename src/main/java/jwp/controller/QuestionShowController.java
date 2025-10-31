package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuestionShowController implements Controller{
    QuestionDao questionDao = new QuestionDao();
    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        Long questionId = Long.parseLong(req.getParameter("questionId"));
        Question question = questionDao.findByQuestionId(questionId);

        req.setAttribute("question", question);

        return "/qna/show.jsp";
    }
}
