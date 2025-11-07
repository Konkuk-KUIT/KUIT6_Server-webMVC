package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.enums.JspUrl;
import jwp.enums.ReqAttribute;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.sql.SQLException;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {
    private final QuestionDao questionDao;

    @GetMapping
    public String home(Model model) throws SQLException {
        model.addAttribute(ReqAttribute.QUESTIONS.getValue(), questionDao.findAll());
        return JspUrl.HOME.getValue();
    }
}
