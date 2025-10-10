package jwp.controller;

import core.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("/user/logout")
//public class LogoutController extends HttpServlet {
public class LogoutController implements Controller {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        //세션에서 사용자 정보 삭제
//        HttpSession session = req.getSession();
//        session.removeAttribute("user");
//
//        //메인 페이지로 리다이렉트
//        resp.sendRedirect("/");
//    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        //세션에서 사용자 정보 삭제
        HttpSession session = req.getSession();
        session.removeAttribute("user");

        //메인 페이지로 리다이렉트
        return "redirect:/";
    }
}
