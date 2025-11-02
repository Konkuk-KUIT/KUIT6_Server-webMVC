package core.mvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// "/"는 JSP를 제외한 모든 요청을 받도록 설정
@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    private RequestMapper requestMapper;

    @Override
    public void init() throws ServletException {
        // RequestMapper를 초기화
        requestMapper = new RequestMapper();
        requestMapper.init();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 요청 URL에 맞는 Controller를 찾는다.
        String requestUri = req.getRequestURI();
        Controller controller = requestMapper.findController(requestUri);

        try {
            // 2. Controller를 실행하고 View 경로를 반환받는다.
            String viewName = controller.execute(req, resp);

            // 3. 반환된 View 경로를 해석하여 Forward 또는 Redirect 처리
            if (viewName.startsWith("redirect:")) {
                // "redirect:" 접두사가 있으면 Redirect
                resp.sendRedirect(viewName.substring("redirect:".length()));
            } else {
                // 없으면 Forward
                RequestDispatcher rd = req.getRequestDispatcher(viewName);
                rd.forward(req, resp);
            }
        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }
}