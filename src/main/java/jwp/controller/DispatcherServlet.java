package jwp.controller;

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
    private final Map<String, Controller> controllerMap = new HashMap<>();

    @Override
    public void init() {
        controllerMap.put("/", new HomeController());
        controllerMap.put("/user/login", new LoginController());
        controllerMap.put("/user/logout", new LogoutController());
        controllerMap.put("/user/signup", new CreateUserController());
        controllerMap.put("/user/list", new ListUserController());
        controllerMap.put("/user/updateForm", new UpdateUserFormController());
        controllerMap.put("/user/update", new UpdateUserController());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        Controller controller = controllerMap.get(requestURI);

        if (controller == null) {
            response.sendRedirect("/");
            return;
        }
        try {
            String viewName = controller.execute(request, response);
            if (viewName == null) return;

            if (viewName.startsWith("redirect:")) {
                response.sendRedirect(viewName.substring("redirect:".length()));
            } else {
                request.getRequestDispatcher(viewName+".jsp").forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
