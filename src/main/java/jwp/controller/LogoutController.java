package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/logout")
public class LogoutController extends HttpServlet {
    //- **Description:** 세션에서 사용자 정보를 삭제하여 로그아웃 처리 후 메인 페이지로 리다이렉트.
//- **Response:** `/` 페이지로 리다이렉트
//- **세션 동작:** HttpSession 에서 `user` 속성 제거
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //세션에서 사용자 정보 삭제
        HttpSession session = req.getSession();
        session.removeAttribute("user");

        //메인 페이지로 리다이렉트
        resp.sendRedirect("/");
    }
}
