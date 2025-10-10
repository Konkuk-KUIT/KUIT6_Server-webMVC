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
    private RequestMapper mapper;

    @Override
    public void init() throws ServletException {
        this.mapper = new RequestMapper();
        mapper.initControllerMap();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        // url에 맞는 controller를 찾아준다.
        Controller controller = mapper.findController(url);

        // 매핑된 컨트롤러가 없으면 404 에러 반환하고 종료
        if(controller == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        try {
            // 해당 컨트롤러의 process() 메서드를 호출해서 로직 처리 → 반환값(view)은 이동할 페이지 정보
            String view = controller.process(req, resp);

            // redirect 방식
            if (view.startsWith("redirect")){
                String redirectPath = view.substring("redirect".length()); // real 경로만 잘라냄
                resp.sendRedirect(redirectPath);
            }else{ // forward 방식
                RequestDispatcher rd = req.getRequestDispatcher(view); // JSP 내부로 이동
                rd.forward(req,resp);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
