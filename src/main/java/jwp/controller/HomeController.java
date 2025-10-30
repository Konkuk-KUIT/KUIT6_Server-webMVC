package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class HomeController implements Controller {
	@Override
	public String service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		QuestionDao questionDao = new QuestionDao();
		Collection<Question> questions = questionDao.findAll();
		req.setAttribute("questions", questions);

		return "/home";
	}
}
