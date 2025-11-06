package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ShowQuestionController {

    private final QuestionDao questionDao;

    @GetMapping("/qna/show")
    public String showQuestion(@RequestParam int questionId, Model model) {
        Question question = questionDao.findByQuestionId(questionId);
        model.addAttribute("question", question);
        return "/qna/show";
    }
}
