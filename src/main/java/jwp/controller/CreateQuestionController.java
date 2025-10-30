package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CreateQuestionController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Question question = new Question(
                0,
                req.getParameter("writer"),
                req.getParameter("title"),
                req.getParameter("contents"),
                Timestamp.valueOf(LocalDateTime.now()),
                0
        );
        QuestionDao.getInstance().insert(question);
        return "redirect:/";
    }
}
