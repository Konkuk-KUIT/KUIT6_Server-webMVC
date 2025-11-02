package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ShowQuestionController implements Controller {

    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String questionIdParam = req.getParameter("questionId");

        try {
            long questionId = Long.parseLong(questionIdParam);

            Question question = questionDao.findByQuestionId(questionId);

            if (question == null) {
                System.out.println("해당 ID의 질문을 찾을 수 없습니다: " + questionId);
                return "redirect:/";
            }

            req.setAttribute("question", question);

            return "/qna/show.jsp";

        } catch (NumberFormatException e) {
            System.out.println("잘못된 questionId 파라미터입니다: " + questionIdParam);
            return "redirect:/";
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("질문 조회 중 DB 오류 발생: " + e.getMessage());
            return "redirect:/";
        }
    }
}