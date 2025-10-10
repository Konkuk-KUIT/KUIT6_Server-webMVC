package jwp.controller;

import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/updateForm")
public class UpdateUserFormController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if(userId != null && userId.equals(user.getUserId())) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/user/updateForm.jsp");
            dispatcher.forward(req, resp);
        }else {
            resp.sendRedirect("/");
        }
    }
}
