package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CreateQuestionController {

    private final QuestionDao questionDao;

    @PostMapping("/qna/create")
    public String createQuestion(String writer, String title, String contents) {
        Question question = new Question(writer, title, contents, 0);
        Question savedQuestion = questionDao.insert(question);
        System.out.println("saved question id= " + savedQuestion.getQuestionId());
        return "redirect:/";
    }
}
