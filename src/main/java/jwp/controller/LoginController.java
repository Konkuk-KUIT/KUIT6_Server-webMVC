package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = MemoryUserRepository.getInstance().findUserById(req.getParameter("userId"));
        if (user == null) {
            System.out.println("아이디를 확인해주세요");
            return "redirect:/user/loginFailed";
        }
        if (user.matchPassword(req.getParameter("password"))) {
            // 세션에 정보 저장
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            System.out.println("로그인 성공");
            //resp.sendRedirect("/");
            return "redirect:/";
        } else {
            System.out.println("비밀번호를 확인해주세요");
            //resp.sendRedirect("/user/loginFailed");
            return "redirect:/user/loginFailed";
        }
    }
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        User user = MemoryUserRepository.getInstance().findUserById(req.getParameter("userId"));
//        if (user == null) {
//            System.out.println("아이디를 확인해주세요");
//            resp.sendRedirect("/user/loginFailed");
//            return;
//        }
//        if (user.matchPassword(req.getParameter("password"))) {
//            // 세션에 정보 저장
//            HttpSession session = req.getSession();
//            session.setAttribute("user", user);
//
//            System.out.println("로그인 성공");
//            resp.sendRedirect("/");
//        } else {
//            System.out.println("비밀번호를 확인해주세요");
//            resp.sendRedirect("/user/loginFailed");
//        }
//    }
}
