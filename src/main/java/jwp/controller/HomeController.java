package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController{

    private final QuestionDao questionDao;

    @GetMapping()
    public String home(Model model) {
        model.addAttribute("questions", questionDao.findAll());
        return "home";
    }
}
