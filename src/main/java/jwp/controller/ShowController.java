package jwp.controller;

import core.web.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ShowController implements Controller {
    private final QuestionDao questionDao = new QuestionDao();
    @Override
    public String handle(HttpServletRequest req, HttpServletResponse resp, Map<String, Object> model) throws Exception {
        String idParam = req.getParameter("questionId");
        if (idParam == null) {
            return "redirect:/"; // 잘못된 요청
        }

        long questionId = Long.parseLong(idParam);
        Question question = questionDao.findById(questionId);

        if (question == null) {
            return "redirect:/";
        }

        model.put("question", question);
        return "/qna/show";
    }
}
