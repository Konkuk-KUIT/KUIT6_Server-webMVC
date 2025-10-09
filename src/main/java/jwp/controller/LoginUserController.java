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
public class LoginUserController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 사용자 ID로 저장소에서 사용자 정보 조회
        User user = MemoryUserRepository.getInstance().findUserById(req.getParameter("userId"));

        // 사용자가 존재하고, 입력된 비밀번호가 일치하는지 확인
        if (user != null && user.matchPassword(req.getParameter("password"))) {
            // 로그인 성공: 세션을 가져와 사용자 정보를 저장
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            // 메인 페이지로 리다이렉트
            resp.sendRedirect("/");
        } else {
            // 로그인 실패: 로그인 실패 페이지로 리다이렉트
            resp.sendRedirect("/user/loginFailed.jsp");
        }
    }
}