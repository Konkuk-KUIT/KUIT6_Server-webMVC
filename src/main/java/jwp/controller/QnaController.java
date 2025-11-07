package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/qna")
@RequiredArgsConstructor
public class QnaController {
    private final QuestionDao questionDao;

    @GetMapping("/form")
    public String showCreateForm(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        boolean loggedIn = session != null && session.getAttribute("user") != null;
        return loggedIn ? "/qna/form" : "redirect:/user/loginForm";
    }

    @PostMapping
    public String create(@RequestParam String title,
                         @RequestParam String contents,
                         HttpSession session) {
        User loginUser = (session == null) ? null : (User) session.getAttribute("user");
        if (loginUser == null) return "redirect:/user/loginForm";

        Question question = new Question(loginUser.getUserId(),title,contents,0);
        Question saved = questionDao.insert(question);
        return "redirect:/qna/show?questionId=" + saved.getQuestionId();
    }

    @GetMapping("/show")
    public String show(@RequestParam("questionId") long questionId, Model model) {
        Question question = questionDao.findById(questionId);
        if (question == null) return "redirect:/";
        model.addAttribute("question", question);
        return "/qna/show";
    }


}
