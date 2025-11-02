package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao; // QuestionDao 임포트
import jwp.model.Question;  // Question 임포트

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List; // List 임포트

public class HomeController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // QuestionDao를 통해 모든 질문을 조회
        QuestionDao questionDao = new QuestionDao();
        List<Question> questions = questionDao.findAll();

        // request에 "questions"라는 이름으로 질문 목록을 저장
        req.setAttribute("questions", questions);

        // View 경로인 "/home.jsp"를 반환
        return "/home.jsp";
    }
}