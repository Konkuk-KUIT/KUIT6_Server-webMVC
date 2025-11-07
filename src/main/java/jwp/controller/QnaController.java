package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.utils.UserSessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
@RequestMapping("/qna")
@RequiredArgsConstructor
public class QnaController {
    private final QuestionDao questionDao;

    //qna form
    @GetMapping("/form")
    public String qnaForm(HttpSession session){
        if (UserSessionUtils.isLogined(session)) {
            return "/qna/form";
        }
        return "redirect:/user/loginForm";
    }

    //qna 생성
    @GetMapping("/create")
    public String createQna(@RequestParam String writer, @RequestParam String title, @RequestParam String contents) {
        Question question = new Question(writer, title, contents);
        try {
            Question qs = questionDao.insert(question);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return "/qna/show";
    }
}
