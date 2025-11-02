package jwp.controller;

import core.mvc.Controller;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowQuestionController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 1. questionId 파라미터 가져오기
        long questionId = Long.parseLong(req.getParameter("questionId"));

        // 2. DAO를 사용해 Question 및 Answer 리스트 조회
        QuestionDao questionDao = new QuestionDao();
        AnswerDao answerDao = new AnswerDao();

        Question question = questionDao.findByQuestionId(questionId);
        List<Answer> answers = answerDao.findAllByQuestionId(questionId);

        // 3. request에 attribute로 저장
        req.setAttribute("question", question);
        req.setAttribute("answers", answers);

        // 4. /qna/show.jsp로 포워딩
        return "/qna/show.jsp";
    }
}