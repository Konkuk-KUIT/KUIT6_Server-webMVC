package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateQuestionController implements Controller {
    @Override
    public String service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String writer = req.getParameter("writer");
        String title = req.getParameter("title");
        String contents = req.getParameter("contents");

        Question question = new Question(title, contents, writer);

        QuestionDao questionDao = new QuestionDao();
        Long successQuestionId = questionDao.insert(question);
        Question successQ = questionDao.findById(successQuestionId);
        System.out.println(successQ);

        return "redirect:/";
    }
}
