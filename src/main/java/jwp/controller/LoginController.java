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

@WebServlet("/user/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 사용자가 입력한 아이디와 비밀번호
        String id = req.getParameter("userId");
        String password = req.getParameter("password");

        User user = MemoryUserRepository.getInstance().findUserById(id);

        // 사용자가 제대로 비밀번호를 입력했는지 확인
        if(user != null && user.matchPassword(password)) {

            // 세션에 유저 정보 저장 후 메인 페이지로 리다이렉트
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            resp.sendRedirect("/");
            return;
        }

        // 로그인 실패 시 리다이렉트
        resp.sendRedirect("/user/login_failed.jsp");
    }
}
