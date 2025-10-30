package jwp.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    Controller controller;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        controller = RequestMapper.getController(req.getRequestURI());

        if(controller==null){
            resp.sendRedirect("/");
        }

        String path = controller.handle(req, resp);

        if(path.startsWith("redirect:/")){
            resp.sendRedirect(path.replaceFirst("redirect:/","/"));
            return;
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher(path);
        dispatcher.forward(req,resp);
    }
}
