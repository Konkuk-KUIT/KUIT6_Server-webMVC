package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowController implements Controller {
    @Override
    public String handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionDao questionDao = new QuestionDao();
        String questionId = request.getParameter("questionId");
        Question question = questionDao.findByQuestionId((Long.parseLong(questionId)));
        request.setAttribute("question", question);
        return "/qna/show.jsp";
    }
}
