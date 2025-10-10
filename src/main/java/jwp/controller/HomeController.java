package jwp.controller;

import core.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/")
//public class HomeController extends HttpServlet {
public class HomeController implements Controller {
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		RequestDispatcher rd = req.getRequestDispatcher("/home.jsp");
//		rd.forward(req, resp);
//	}


	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		return "/home";
	}
}
