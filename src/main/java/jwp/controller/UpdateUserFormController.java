package jwp.controller;

import jwp.model.User;
import util.SessionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// "/user/updateForm"
public class UpdateUserFormController implements Controller {
    @Override
    public String handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        if (!SessionUtil.isLogin(request)) {
            return "redirect:/user/login.html";
        }

        if (!SessionUtil.isOwner(request, userId)) {
            return "redirect:/";
        }

        if(user!=null && user.getUserId().equals(userId)){
            return "/user/updateForm.jsp";
        }

        return "redirect:/";
    }
}
