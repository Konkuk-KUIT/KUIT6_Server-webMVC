package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

//@WebServlet("/")
//public class HomeController extends HttpServlet {
public class HomeController implements Controller {
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		RequestDispatcher rd = req.getRequestDispatcher("/home.jsp");
//		rd.forward(req, resp);
//	}

	//quesiton 객체 가져오기
	private final QuestionDao questionDao = new QuestionDao();

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
			//DB에서 quesiton 목록 조회
			List<Question> questionList = questionDao.findAll();
			//JSP로 넘길 데이터 설정
			req.setAttribute("questions", questionList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "/home";
	}
}
