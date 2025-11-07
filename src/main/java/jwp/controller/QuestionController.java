package jwp.controller;

import jwp.dao.AnswerDao;
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

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/qna")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionDao questionDao;
    private final AnswerDao answerDao;

    /**
     * 질문 상세 보기 (ShowQuestionController 역할)
     */
    @GetMapping("/show")
    public String show(@RequestParam("questionId") Long questionId, Model model) throws Exception {
        model.addAttribute("question", questionDao.findByQuestionId(questionId));
        model.addAttribute("answers", answerDao.findAllByQuestionId(questionId));
        return "qna/show";
    }

    /**
     * 질문 작성 폼 (CreateQuestionFormController 역할)
     */
    @GetMapping("/form")
    public String questionForm(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/user/loginForm";
        }
        return "qna/form";
    }

    /**
     * 질문 작성 (CreateQuestionController 역할)
     */
    @PostMapping("/create")
    public String create(@RequestParam("title") String title,
                         @RequestParam("contents") String contents,
                         HttpSession session) throws Exception {

        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser == null) {
            return "redirect:/user/loginForm";
        }

        Question newQuestion = new Question(sessionUser.getUserId(), title, contents);
        questionDao.insert(newQuestion);

        return "redirect:/";
    }
}