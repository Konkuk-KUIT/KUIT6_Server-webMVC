package jwp.controller;

import core.mvc.Controller;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {


        long questionId = Long.parseLong(req.getParameter("questionId"));
        Question question = QuestionDao.getInstance()
                .findByQuestionId(questionId);
        List<Answer> answers = AnswerDao.getInstance()
                .findByQuestionId(questionId);

        if(question != null){
            req.setAttribute("question", question);
            req.setAttribute("answers", answers);
            return "/qna/show.jsp";
        }

        return "redirect:/";
    }
}
