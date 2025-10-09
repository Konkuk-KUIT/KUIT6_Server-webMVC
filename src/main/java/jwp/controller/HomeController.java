package jwp.controller;

import core.web.mvc.Controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class HomeController implements Controller {
	@Override
    public String handle(HttpServletRequest req, HttpServletResponse resp, Map<String, Object> model) throws Exception {
        return "/index";
    }
}
