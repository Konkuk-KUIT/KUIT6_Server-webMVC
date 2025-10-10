package jwp.controller;

import core.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 홈 페이지를 보여주는 컨트롤러
 */
public class ForwardHomeController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// home.jsp로 forward
		return "/home";
	}
}
