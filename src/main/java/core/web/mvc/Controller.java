package core.web.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface Controller {
    String handle(HttpServletRequest req, HttpServletResponse resp, Map<String,Object> model) throws Exception;
}
