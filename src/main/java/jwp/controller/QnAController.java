package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;
import jwp.util.UserSessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/qna")
@RequiredArgsConstructor
public class QnAController {

    private final QuestionDao questionDao;

    @PostMapping("/create")
    public String createQuestion(@ModelAttribute Question question) throws Exception {
        Question savedQuestion = questionDao.insert(question);
        System.out.println("saved question id= " + savedQuestion.getQuestionId());
        return "redirect:/";
    }

    @GetMapping("/show")
    public String showQuestions(@RequestParam String questionId, Model model) throws Exception {
        Question question = questionDao.findByQuestionId(Integer.parseInt(questionId));
        model.addAttribute("question", question);
        return "qna/show";
    }

    @PostMapping("/update")
    public String updateQuestion(HttpSession session, @RequestParam String questionId,
                         @RequestParam String title, @RequestParam String contents) throws Exception {
        if (!UserSessionUtils.isLogined(session)) {
            return "redirect:/users/loginForm";
        }

        User loginUser = UserSessionUtils.getUserFromSession(session);
        Question question = questionDao.findByQuestionId(Integer.parseInt(questionId));
        if (!question.isSameUser(loginUser)) {
            throw new IllegalArgumentException("로그인된 유저와 질문 작성자가 다르면 질문을 수정할 수 없습니다.");
        }
        question.updateTitleAndContents(title, contents);
        questionDao.update(question);

        return "redirect:/";
    }

    @GetMapping("/form")
    public String createForm(HttpSession session) {
        if (UserSessionUtils.isLogined(session)) {          // 회원만 질문 등록 가능
            return "qna/form";
        }
        return "redirect:/user/loginForm";
    }

    @GetMapping("/updateForm")
    public String updateQuestionForm(HttpSession session, @RequestParam String questionId
    , Model model) throws Exception {
        if (!UserSessionUtils.isLogined(session)) {          // 회원만 질문 등록 가능
            return "redirect:/user/loginForm";
        }
        Question question = questionDao.findByQuestionId(Integer.parseInt(questionId));
        User loginUser = UserSessionUtils.getUserFromSession(session);
        if (question == null || !question.isSameUser(loginUser)) {
            return "redirect:/";
        }

        model.addAttribute("question", question);
        return "qna/updateForm";
    }
}