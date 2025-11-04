package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;
import jwp.util.UserSessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/qna")
public class QuestionController {

    private final QuestionDao questionDao;

    @PostMapping("/create")
    public String createQuestion(@RequestParam String writer,
                                 @RequestParam String title,
                                 @RequestParam String contents) throws Exception {

        Question question = new Question(writer, title, contents, 0);
        Question savedQuestion = questionDao.insert(question);

        System.out.println("saved question id = " + savedQuestion.getQuestionId());
        return "redirect:/";
    }

    @GetMapping("/form")
    public String showQuestionForm(HttpSession session) {
        if (UserSessionUtils.isLogined(session)) {
            System.out.println(2);
            return "qna/form";
        }
        return "redirect:/user/loginForm";
    }

    @GetMapping("/show")
    public String showQuestion(@RequestParam("questionId") int questionId, Model model) throws Exception {
        Question question = questionDao.findByQuestionId(questionId);
        model.addAttribute("question", question);
        return "qna/show";
    }

    @PostMapping("/update")
    public String updateQuestion(@RequestParam("questionId") int questionId,
                                 @RequestParam("title") String title,
                                 @RequestParam("contents") String contents,
                                 HttpSession session) throws Exception {
        if (!UserSessionUtils.isLogined(session)) {
            return "redirect:/user/loginForm";
        }
        User user = UserSessionUtils.getUserFromSession(session);
        Question question = questionDao.findByQuestionId(questionId);

        if (!question.isSameUser(user)) {
            throw new IllegalArgumentException("로그인된 유저와 질문 작성자가 다르면 질문을 수정할 수 없습니다.");
        }
        question.updateTitleAndContents(title, contents);
        questionDao.update(question);

        return "redirect:/";
    }

    @GetMapping("/updateForm")
    public String showUpdateForm(@RequestParam("questionId") int questionId,
                                 HttpSession session,
                                 Model model) throws Exception {
        if (!UserSessionUtils.isLogined(session)) {
            return "redirect:/user/loginForm";
        }
        Question question = questionDao.findByQuestionId(questionId);
        User user = UserSessionUtils.getUserFromSession(session);

        if (!question.isSameUser(user)) {
            throw new IllegalArgumentException("로그인된 사용자와 질문 작성자가 다릅니다.");
        }
        model.addAttribute("question", question);
        return "qna/updateForm";
    }
}
