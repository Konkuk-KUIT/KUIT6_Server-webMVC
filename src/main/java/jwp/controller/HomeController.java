package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;

import java.util.Collection;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class HomeController{
	private final QuestionDao questionDao;

	@GetMapping("/")
	public String service(Model model)  {
		Collection<Question> questions = questionDao.findAll();
		model.addAttribute("questions", questions);
		return "/home";
	}
}
