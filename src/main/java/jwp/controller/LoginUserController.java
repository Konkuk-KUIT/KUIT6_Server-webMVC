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
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        User user = MemoryUserRepository.getInstance().findUserById(userId);

        // 로그인 성공 : user가 존재하고, 비밀번호가 일치하면 세션을 가져오거나 만들어 사용자 정보 저장
        if (user != null && user.matchPassword(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            System.out.println("user 로그인 성공");
            resp.sendRedirect("/");
        } else { // 로그인 실패 : 실패 페이지로 redirect
            resp.sendRedirect("/user/loginFailed.jsp");
        }

    }

}
