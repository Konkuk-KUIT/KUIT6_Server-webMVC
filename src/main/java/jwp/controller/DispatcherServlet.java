package jwp.controller;

import jwp.mapper.RequestMapper;

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

        String path = req.getRequestURI();
        System.out.println("path: " + path);

        Controller controller = requestMapper.mappedController(path);

        if(controller == null){
            resp.sendError(404, "Not Found");
            return;
        }

        String viewName = controller.handleRequest(req, resp);

        render(viewName, req, resp);

    }

    private void render(String viewName, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(viewName == null){
            resp.sendError(404, "Not Found");
            return;
        }

        if(viewName.startsWith("redirect:")){
            String redirect = viewName.replaceFirst("redirect:", "");
            resp.sendRedirect(redirect);
            System.out.println("redirect: " + redirect);
        } else {
            String path = viewName;
            req.getRequestDispatcher(path).forward(req, resp);
            System.out.println("forward: " + path);
        }

    }
}
