package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.enums.HttpUrl;
import jwp.enums.JspUrl;
import jwp.enums.QuestionParameter;
import jwp.enums.ReqAttribute;
import jwp.model.Question;
import jwp.util.UserSessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@Controller
@RequestMapping("/qna")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionDao questionDao;

    @GetMapping("/form")
    public String showQuestionForm(HttpSession session){
        if (UserSessionUtils.isLogined(session)) {
            return JspUrl.QNA_FORM.getValue();
        }
        return HttpUrl.LOGIN.redirect();
    }

    @PostMapping("/create")
    public String createQuestion(@RequestParam("writer") String writer, @RequestParam("title") String title, @RequestParam("contents") String contents) throws SQLException {
        Question question = new Question(writer, title, contents);
        questionDao.insert(question);
        return HttpUrl.ROOT.redirect();
    }

    @GetMapping("/show")
    public String showQuestion(@RequestParam("questionId") Long questionId, Model model) throws SQLException {
        Question question = questionDao.findByQuestionId(questionId);
        model.addAttribute(ReqAttribute.QUESTION.getValue(), question);
        return JspUrl.QNA_SHOW.getValue();
    }
}
