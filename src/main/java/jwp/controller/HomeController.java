package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.dao.UserDao;
import jwp.model.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class HomeController implements Controller {
	private final QuestionDao questionDao = new QuestionDao();

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			List<Question> questions = questionDao.findAll();
			req.setAttribute("questions", questions);
		}  catch (SQLException e) {
			e.printStackTrace();
		}
		return "/home.jsp";
	}

//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		RequestDispatcher rd = req.getRequestDispatcher("/home.jsp");
//		rd.forward(req, resp);
//	}
}
