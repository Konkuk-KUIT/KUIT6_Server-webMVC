package jwp.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface Controller {
    String process(HttpServletRequest res, HttpServletResponse resp);
}
