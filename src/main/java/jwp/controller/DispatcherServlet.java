package jwp.controller;

import jwp.enums.HttpUrl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI().substring(req.getContextPath().length());
        Controller controller = RequestMapper.getInstance().getValue(path);
        String url = null;
        try {
            url = controller.service(req, resp);
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unknown Exception: " + e.getMessage());
        }
        if(controller==null){
            resp.sendRedirect(HttpUrl.ROOT.getValue());
            return;
        }
        if(Objects.requireNonNull(url).startsWith(HttpUrl.REDIRECT.getValue())) {
            url = url.replace(HttpUrl.REDIRECT.getValue(), "");
            resp.sendRedirect(url);
        }  else {
            RequestDispatcher dispatcher = req.getRequestDispatcher(url);
            dispatcher.forward(req, resp);
        }
    }
}
