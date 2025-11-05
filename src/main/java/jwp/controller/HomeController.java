package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    private final QuestionDao questionDao;

    @GetMapping("/")
            // Model : Spring이 내부적으로 이 객체 만들어서 View로 데이터 넘겨준다.
    public String home(Model model) throws Exception {
        List<Question> questions = questionDao.findAll();

        log.info("HomeController 호출됨 - 조회된 질문 개수: {}", questions.size());

        model.addAttribute("questions", questions);

        return "/home.jsp";
    }
}