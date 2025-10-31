package jwp.controller;


import core.web.mvc.Controller;

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
    private final Map<String, Controller> routes = new HashMap<>();

    @Override
    public void init(){
        registerRoutes();
    }

    private void registerRoutes(){
        routes.put("/", (Controller) new HomeController());
        routes.put("/user/login",          new LoginController());
        routes.put("/user/logout",         new LogoutController());
        routes.put("/user/create",         new CreateUserController());
        routes.put("/user/list",           new ListUserController());
        routes.put("/user/updateForm",     new UpdateUserFormController());
        routes.put("/user/update",         new UpdateUserController());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI().substring(req.getContextPath().length());

        Controller handler = routes.get(path);
        if (handler == null) { resp.sendError(HttpServletResponse.SC_NOT_FOUND); return; }

        Map<String,Object> model = new HashMap<>();
        String view;
        try {
            view = handler.handle(req, resp, model);
        } catch (Exception e) {
            throw new ServletException(e);
        }

        model.forEach(req::setAttribute);

        if (view.startsWith("redirect:")) {
            resp.sendRedirect(view.substring("redirect:".length()));
        } else {
            String jsp = "/WEB-INF/views" + view + ".jsp";
            req.getRequestDispatcher(jsp).forward(req, resp);
        }

    }
}
