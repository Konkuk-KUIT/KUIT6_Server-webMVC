package core.mvc;

import jwp.controller.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 모든 요청을 받아서 적절한 컨트롤러로 분기하는 디스패처 서블릿
 * MVC 패턴의 Front Controller 역할을 수행한다
 */
@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(DispatcherServlet.class.getName());
    private static final String REDIRECT_PREFIX = "redirect:";

    private RequestMapper requestMapper;

    @Override
    public void init() throws ServletException {
        // RequestMapper 초기화 및 URL 매핑 설정
        requestMapper = new RequestMapper();
        initControllerMappings();
        logger.log(Level.INFO, "DispatcherServlet 초기화 완료");
    }

    /**
     * 컨트롤러 매핑을 초기화
     * 각 URL 패턴에 대응하는 컨트롤러를 등록한다
     */
    private void initControllerMappings() {
        requestMapper.addMapping("/", new ForwardHomeController());
        requestMapper.addMapping("/user/form", new ForwardUserFormController());
        requestMapper.addMapping("/user/login", new ForwardLoginController());
        requestMapper.addMapping("/user/loginFailed", new ForwardLoginFailedController());
        requestMapper.addMapping("/user/list", new ForwardListUserController());
        requestMapper.addMapping("/user/updateForm", new ForwardUpdateFormController());

        requestMapper.addMapping("/user/signup", new CreateUserController());
        requestMapper.addMapping("/user/loginAction", new LoginController());
        requestMapper.addMapping("/user/logout", new LogoutController());
        requestMapper.addMapping("/user/update", new UpdateUserController());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        logger.log(Level.INFO, "요청 URI: " + requestURI);

        // RequestMapper에서 해당 URL에 맞는 컨트롤러 찾기
        Controller controller = requestMapper.getController(requestURI);

        if (controller == null) {
            logger.log(Level.WARNING, "매핑된 컨트롤러를 찾을 수 없음: " + requestURI);
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        try {
            // 컨트롤러 실행 및 뷰 경로 받기
            String viewPath = controller.execute(req, resp);

            if (viewPath == null) {
                // 컨트롤러에서 직접 응답을 처리한 경우
                return;
            }

            // redirect 또는 forward 처리
            processView(viewPath, req, resp);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "컨트롤러 실행 중 오류 발생", e);
            throw new ServletException(e);
        }
    }

    /**
     * 뷰 경로를 해석하여 redirect 또는 forward 처리
     *
     * @param viewPath 컨트롤러가 반환한 뷰 경로
     * @param req HTTP 요청 객체
     * @param resp HTTP 응답 객체
     */
    private void processView(String viewPath, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (viewPath.startsWith(REDIRECT_PREFIX)) {
            // redirect: 처리
            String redirectUrl = viewPath.substring(REDIRECT_PREFIX.length());
            logger.log(Level.INFO, "Redirect to: " + redirectUrl);
            resp.sendRedirect(redirectUrl);
        } else {
            // forward 처리
            String forwardPath = viewPath;
            if (!forwardPath.endsWith(".jsp")) {
                forwardPath = viewPath + ".jsp";
            }
            logger.log(Level.INFO, "Forward to: " + forwardPath);
            RequestDispatcher rd = req.getRequestDispatcher(forwardPath);
            rd.forward(req, resp);
        }
    }
}
