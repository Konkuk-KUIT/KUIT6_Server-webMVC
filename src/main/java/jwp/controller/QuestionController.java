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
import java.sql.Date;
import java.time.LocalDate;

@Controller
@RequestMapping("/qna")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionDao questionDao;

    @GetMapping("/form")
    public String createQuestionForm(HttpSession session) {
        if(UserSessionUtils.isLogined(session)) {
            return "qna/form";
        }
        return "redirect:/loginForm";
    }

    @PostMapping("/updateForm")
    public String updateQuestionForm(HttpSession session, @RequestParam String questionId, Model model) {
        if(!UserSessionUtils.isLogined(session)) {
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
    public String updateQuestion(
            HttpSession session,
            @RequestParam int questionId,
            @RequestParam String title,
            @RequestParam String contents,
            Model model
    ) {
        if(!UserSessionUtils.isLogined(session)) {
            return "redirect:/user/loginForm";
        }

        User user = UserSessionUtils.getUserFromSession(session);
        Question question = questionDao.findByQuestionId(questionId);
        if(!question.isSameUser(user)) {
            throw new IllegalArgumentException("로그인된 유저와 질문 작성자가 다르면 질문을 수정할 수 없습니다.");
        }
        question.updateTitleAndContents(title, contents);
        questionDao.update(question);
        return "redirect:/";
    }

    @PostMapping("/create")
    public String createQuestion(@ModelAttribute Question question, HttpSession session) {
        System.out.println(question.getQuestionId() + "   " + question.getTitle() + "   " + question.getContents());

        question.setCreatedDate(Date.valueOf(LocalDate.now()));

        Question savedQuestion = questionDao.insert(question);
        System.out.println("saved quesition id = " + savedQuestion.getQuestionId());
        return "redirect:/";
    }

    @GetMapping("/show")
    public String showQuestion(@RequestParam int questionId, Model model) {
        Question question = questionDao.findByQuestionId(questionId);
        model.addAttribute("question", question);
        return "qna/show";
    }

}
