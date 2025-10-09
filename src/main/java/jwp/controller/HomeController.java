package jwp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController implements Controller {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) {
		return "/home.jsp";
	}
}
