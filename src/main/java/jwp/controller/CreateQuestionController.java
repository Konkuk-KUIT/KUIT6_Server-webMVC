package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CreateQuestionController implements Controller {

    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Question newQuestion = new Question(
                req.getParameter("writer"),
                req.getParameter("title"),
                req.getParameter("contents")
        );

        try {
            Question savedQuestion = questionDao.insert(newQuestion);

            return "redirect:/qna/show?questionId=" + savedQuestion.getQuestionId();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("질문 생성 중 DB 오류: " + e.getMessage());
            req.setAttribute("errorMessage", "질문 등록에 실패했습니다.");
            return "/qna/form.jsp";
        }
    }
}