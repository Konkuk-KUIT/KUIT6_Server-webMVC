package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import util.SessionUtil;
import util.UserSessionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/qna")
@RequiredArgsConstructor
public class QnaController {
    private final QuestionDao questionDao;

    @PostMapping("/create")
    public String createQuestion(@ModelAttribute Question question) throws ServletException, IOException {
        questionDao.insert(question);
        return "redirect:/";
    }
    @GetMapping("/form")
    public String form(HttpSession session) {
        if (UserSessionUtils.isLogined(session)) {
            return "/qna/form";
        }
        return "redirect:/user/login";
    }
    @GetMapping("/show")
    public String show(@RequestParam("questionId") String questionId, Model model) throws ServletException, IOException {
        Question question = questionDao.findByQuestionId((Long.parseLong(questionId)));
        model.addAttribute("question", question);
        return "qna/show";
    }
}
