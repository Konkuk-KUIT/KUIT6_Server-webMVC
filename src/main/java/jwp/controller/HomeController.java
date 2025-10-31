package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/home")
public class HomeController extends HttpServlet {
    private final QuestionDao questionDao = new QuestionDao();

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Question> questions = questionDao.findAll();
        req.setAttribute("questions", questions);

        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}
