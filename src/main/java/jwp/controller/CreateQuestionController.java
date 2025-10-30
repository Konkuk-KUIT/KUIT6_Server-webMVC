package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateQuestionController implements Controller {
    @Override
    public String handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Question question = new Question(
                request.getParameter("writer"),
                request.getParameter("title"),
                request.getParameter("contents"));

        QuestionDao questionDao = new QuestionDao();
        questionDao.insert(question);
        return "redirect:/";
    }
}
