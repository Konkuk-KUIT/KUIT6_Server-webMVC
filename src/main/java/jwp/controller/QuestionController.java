package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/qna")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionDao questionDao;

    @GetMapping("/form")
    public String questionForm(HttpSession session) {
        if ((User) session.getAttribute("user") == null) {
            return "redirect:" + "/user/login.jsp";
        }
        return "qna/form";
    }

    @PostMapping("/create")
    public String createQuestion(HttpSession session, @ModelAttribute Question question) {
        if ((User) session.getAttribute("user") == null) {
            return "redirect:" + "/user/login.jsp";
        }
        questionDao.insert(question);
        return "redirect:" + "/";
    }

    @GetMapping("/show")
    public String process(@RequestParam long questionId, Model model) {
        model.addAttribute("question",questionDao.findByQuestionId(questionId));

        return "qna/show";
    }

}
