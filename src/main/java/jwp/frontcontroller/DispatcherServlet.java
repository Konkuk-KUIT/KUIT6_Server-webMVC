package jwp.frontcontroller;

import jwp.controller.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        String method = req.getMethod();
        System.out.println(url);

        MappingController mappingController = new MappingController();
        Controller controller = mappingController.getController(url);
        String processedUrl = "";

        if("GET".equals(method)){
            processedUrl = controller.executeGet(req,resp);
        } else if("POST".equals(method)){
            processedUrl = controller.executePost(req,resp);
        }

        if(processedUrl.startsWith("redirect")){
            resp.sendRedirect(processedUrl.split(":")[1]);
        } else {
            RequestDispatcher rd = req.getRequestDispatcher(processedUrl);
            rd.forward(req,resp);
        }
    }
}
