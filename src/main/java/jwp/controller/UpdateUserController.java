package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateUserController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User sessionUser = (User) session.getAttribute("user");
        String updateUserId = req.getParameter("userId");
        if (sessionUser != null && sessionUser.isSameUser(updateUserId)) {
            User user = new User(updateUserId, req.getParameter("password"), req.getParameter("name"), req.getParameter("email"));
            MemoryUserRepository.getInstance().changeUserInfo(user);
            session.setAttribute("user", user);
            return "redirect:/user/list";
        } else {
            return "redirect:/";
        }
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////        User user = new User(req.getParameter("userId"), req.getParameter("password"), req.getParameter("name"), req.getParameter("email"));
////        MemoryUserRepository.getInstance().changeUserInfo(user);
////        resp.sendRedirect("/user/list");
//        HttpSession session = req.getSession();
//        User sessionUser = (User) session.getAttribute("user");
//        String updateUserId = req.getParameter("userId");
//        if (sessionUser != null && sessionUser.isSameUser(updateUserId)) {
//            User user = new User(updateUserId, req.getParameter("password"), req.getParameter("name"), req.getParameter("email"));
//            MemoryUserRepository.getInstance().changeUserInfo(user);
//            session.setAttribute("user", user);
//            resp.sendRedirect("/user/list");
//        } else {
//            resp.sendRedirect("/");
//        }
//    }
}