package jwp.controller;

import jwp.controller.mapping.RequestMapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    private RequestMapper requestMapper = RequestMapper.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String requestURI = req.getRequestURI();
        Controller controller = requestMapper.mappingController(requestURI);

        if (controller == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String viewName = controller.process(req, resp);
        viewResolve(req, resp, viewName);
    }

    private static void viewResolve(HttpServletRequest req, HttpServletResponse resp, String viewName) throws IOException, ServletException {
        if (viewName.contains(Controller.REDIRECT_PREFIX)) {
            resp.sendRedirect(viewName.substring(Controller.REDIRECT_PREFIX.length()));
            return;
        }
        RequestDispatcher rd = req.getRequestDispatcher(viewName);
        rd.forward(req, resp);
    }
}
