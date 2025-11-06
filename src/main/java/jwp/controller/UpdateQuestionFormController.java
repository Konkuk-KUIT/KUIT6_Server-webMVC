package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;
import jwp.util.UserSessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class UpdateQuestionFormController {

    private final QuestionDao questionDao;

    @GetMapping("/qna/updateForm")
    public String showUpdateQuestionForm(@RequestParam int questionId, Model model, HttpSession session) {
        if (!UserSessionUtils.isLogined(session)) {  // 회원만 질문 등록 가능
            return "redirect:/user/loginForm";
        }
        Question question = questionDao.findByQuestionId(questionId);
        User user = UserSessionUtils.getUserFromSession(session);
        if (!question.isSameUser(user)) {
            throw new IllegalArgumentException();
        }
        model.addAttribute("question", question);
        return "/qna/updateForm";
    }
}
