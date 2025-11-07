package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/qna")
@RequiredArgsConstructor
public class QnaController {
    private final QuestionDao questionDao;

    @GetMapping("/form")
    public String showQuestionForm(HttpSession session) {
        if (!UserSessionUtils.isLogined(session)) {
            return "redirect:/user/loginForm";
        }
        return "qna/form";
        // 화살촉 안티패턴을 피하고 Guard Clause 사용을 위해 비로그인 처리 로직을 위의 if문에
    }

    @PostMapping("/create")
    public String createQuestion(
            @RequestParam("title") String title,
            @RequestParam("contents") String contents,
            HttpSession session
    ) {
        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser == null) {
            return "redirect:/user/loginForm";
        }

        Question questionToSave = new Question(
                sessionUser.getUserId(),
                title,
                contents
        );

        questionDao.insert(questionToSave);

        return "redirect:/qna/show?questionId=" + questionToSave.getQuestionId();
    }
}
