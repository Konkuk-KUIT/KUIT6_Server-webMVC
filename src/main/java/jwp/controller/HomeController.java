package jwp.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// /
public class HomeController implements Controller {

    @Override
    public String handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "home.jsp";
    }


}
