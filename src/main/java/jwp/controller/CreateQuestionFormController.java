package jwp.controller;

import jwp.util.UserSessionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CreateQuestionFormController {

    @GetMapping("/qna/form")
    public String showQuestionForm(HttpSession session) {
        if (UserSessionUtils.isLogined(session)) {  // 회원만 질문 등록 가능
            return "/qna/form";
        }
        return "redirect:/user/loginForm";
    }
}
