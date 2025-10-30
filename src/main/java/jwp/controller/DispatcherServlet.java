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
    Controller controller;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        controller = RequestMapper.getController(req.getRequestURI());
        System.out.println("요청 URI 확인: " + req.getRequestURI()); // 💡 이 코드를 추가
        if(controller==null){
            resp.sendRedirect("/");
            return;
        }
        String path = controller.service(req, resp);
        if(path.startsWith("redirect:/")){
            resp.sendRedirect(path.replaceFirst("redirect:/","/"));
            return;
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher(path+".jsp");
        dispatcher.forward(req,resp);
    }
}
