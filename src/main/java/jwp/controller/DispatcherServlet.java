package jwp.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    private final RequestMapper requestMapper = new RequestMapper();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        Controller controller = requestMapper.getController(url);
        if (controller == null) {
            resp.sendRedirect("/");
            return;
        }

        String result = controller.execute(req, resp);
        if (result.startsWith("redirect:")) {
            resp.sendRedirect(result.substring("redirect:".length()));
        } else if (result.startsWith("/")) {
            RequestDispatcher rd = req.getRequestDispatcher(result);
            rd.forward(req, resp);
        }
    }
}
