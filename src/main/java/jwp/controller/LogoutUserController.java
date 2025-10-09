package jwp.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/logout")
public class LogoutUserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 현재 세션을 가져옴
        HttpSession session = req.getSession();

        // 세션에서 "user" 속성을 제거
        session.removeAttribute("user");

        // 메인 페이지로 리다이렉트
        resp.sendRedirect("/");
    }
}