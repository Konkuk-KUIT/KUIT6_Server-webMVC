package core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
    /**
     * @return Forward 또는 Redirect할 View의 경로
     */
    String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}