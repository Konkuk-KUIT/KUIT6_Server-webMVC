package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;
import jwp.util.UserSessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
@RequestMapping("/qna")
@RequiredArgsConstructor
public class QnaController {
    private final QuestionDao questionDao;

    @GetMapping("/form")
    public String createQuestionForm(HttpSession session) {
        if (UserSessionUtils.isLogined(session)) {
            return "qna/form";
        }
        return "redirect:/user/loginForm";
    }

    @GetMapping("/updateForm")
    public String updateQuestionForm(@RequestParam String questionId, HttpSession session, Model model) throws Exception {
        if (UserSessionUtils.isLogined(session)) {
            return "redirect:/user/loginForm";
        }
        Question question = questionDao.findByQuestionId(Integer.parseInt(questionId));
        User user = UserSessionUtils.getUserFromSession(session);
        if(!question.isSameUser(user)) {
            throw new IllegalArgumentException();
        }
        model.addAttribute("question", question);
        return "qna/updateForm";
    }

    @PostMapping("/update")
    public String updateQuestion(@RequestParam String questionId, @RequestParam String title, @RequestParam String contents, HttpSession session, Model model) throws Exception {
        if (!UserSessionUtils.isLogined(session)) {
            return "redirect:/users/loginForm";
        }
        User user = UserSessionUtils.getUserFromSession(session);
        Question question = questionDao.findByQuestionId(Integer.parseInt(questionId));
        if (!question.isSameUser(user)) {
            throw new IllegalArgumentException("로그인된 유저와 질문 작성자가 다르면 질문을 수정할 수 없습니다.");
        }
        question.updateTitleAndContents(title,
                contents);
        questionDao.update(question);
        return "redirect:/";
    }

    @PostMapping("/create")
    public String createQuestion(@ModelAttribute Question question) throws Exception {
        Question savedQuestion = questionDao.insert(question);
        System.out.println("saved question id= " + savedQuestion.getQuestionId());
        return "redirect:/";
    }

    @GetMapping("/show")
    public String showQuestion(@RequestParam String questionId, HttpSession session, Model model) throws Exception {
        Question question = questionDao.findByQuestionId(Integer.parseInt(questionId));
        model.addAttribute("question", question);
        return "qna/show";
    }
}
