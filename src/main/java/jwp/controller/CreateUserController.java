package jwp.controller;

import core.db.MemoryUserRepository;
import core.mvc.Controller;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/signup")
//public class CreateUserController extends HttpServlet {
public class CreateUserController implements Controller {
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        User user = new User(req.getParameter("userId"),
//                req.getParameter("password"),
//                req.getParameter("name"),
//                req.getParameter("email"));
//        MemoryUserRepository.getInstance().addUser(user);
//        System.out.println("user 회원가입 완료");
////        resp.sendRedirect("/user/list");
//        resp.sendRedirect("/");
//    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email"));
        MemoryUserRepository.getInstance().addUser(user);
        System.out.println("user 회원가입 완료");
        return "redirect:/";
    }
}
