package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ShowController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        QuestionDao questionDao = new QuestionDao();
        Question question = null;
        String questionId = req.getParameter("questionId");
        try {
            question = questionDao.findByQuestionId(Long.parseLong(questionId));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("question", question);
        return "qna/show.jsp";
    }
}
