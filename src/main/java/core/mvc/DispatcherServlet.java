package core.mvc;

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
    public void init() throws ServletException {
        //RequestMapper 객체 생성 및 초기 맵핑
//        requestMapper = new RequestMapper();
//        requestMapper.init();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // ✅ 요청 경로 확인용 로그
        System.out.println("RequestURI: " + req.getRequestURI());
        System.out.println("ContextPath: " + req.getContextPath());
        System.out.println("ServletPath: " + req.getServletPath());
        String path = req.getRequestURI();
//        String path = req.getRequestURI().substring(req.getContextPath().length());
        Controller controller = requestMapper.get(path);

        if (controller == null) {
            System.out.println("⚠️ No Controller found for path: " + path);
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String url = controller.execute(req, resp);
        System.out.println("➡️ Forward/Redirect URL: " + url);

        if (url.startsWith("redirect:")) {
            // redirect:/user/list → /user/list 로 잘라서 redirect
            String redirectUrl = url.substring("redirect:".length());
            resp.sendRedirect(redirectUrl);
        } else {
            // 그냥 /user/list.jsp 형태면 forward
            RequestDispatcher rd = req.getRequestDispatcher(url + ".jsp");
            rd.forward(req, resp);
        }
    }
}
