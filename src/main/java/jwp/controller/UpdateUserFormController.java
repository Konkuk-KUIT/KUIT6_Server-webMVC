package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateUserFormController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User sessionUser = (User) session.getAttribute("user");
        String updateUserId = req.getParameter("userId");

        if (sessionUser != null && sessionUser.isSameUser(updateUserId)) {
            User user = MemoryUserRepository.getInstance().findUserById(updateUserId);
            req.setAttribute("user", user);
            return "/user/updateForm.jsp";
        } else {
            return "redirect:/";
        }
    }
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        User sessionUser = (User) session.getAttribute("user");
//        String updateUserId = req.getParameter("userId");
//
//        if (sessionUser != null && sessionUser.isSameUser(updateUserId)) {
//            User user = MemoryUserRepository.getInstance().findUserById(updateUserId);
//            req.setAttribute("user", user);
//            RequestDispatcher rd = req.getRequestDispatcher("/user/updateForm.jsp");
//            rd.forward(req, resp);
//        } else {
//            resp.sendRedirect("/");
//        }
//    }
}
