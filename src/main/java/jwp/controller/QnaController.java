package jwp.controller;


import jwp.dao.QuestionDao;
import jwp.dao.UserDao;
import jwp.model.Question;
import jwp.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/qna")
@RequiredArgsConstructor
public class QnaController {
    private final QuestionDao questionDao;
    private final UserDao userDao;

    @GetMapping("/form")
    public String qnaForm(HttpSession session) throws ServletException, IOException {
        User user = (User) session.getAttribute("user");
        System.out.println(user);
        if(user != null && userDao.findByUserId(user.getUserId())!=null) {
            System.out.println("자 제가 한게 맞습니다.");
            return "qna/form";
        }
        System.out.println("아닙니다 넌 틀렸습니다.");

        return "redirect:/";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Question question) throws ServletException, IOException {

        questionDao.insert(question);
        return "redirect:/";
    }

    @GetMapping("/show")
    public String show(@RequestParam String questionId, Model model) {
        Question question = questionDao.findById((Long.parseLong(questionId)));
        model.addAttribute("question", question);
        return "qna/show";
    }


}
