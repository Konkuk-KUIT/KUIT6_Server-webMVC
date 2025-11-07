package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServlet;
import java.util.List;


@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController extends HttpServlet {
    private final QuestionDao questionDao;

	@GetMapping
    public String home(Model model) throws Exception {
        List<Question> questions = questionDao.findAll();
        model.addAttribute("questions", questions);
        return "home";
    }
}
