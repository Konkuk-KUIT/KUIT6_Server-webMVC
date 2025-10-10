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
        User LoginUserInfo = new User(req.getParameter("userId"), req.getParameter("password"), null, null);
        User targetUser = null;
        HttpSession session;
        MemoryUserRepository repo = MemoryUserRepository.getInstance();

        //사용자 인증
        boolean flag = false;
        for (User user : repo.findAll()) {
//            if(user.isSameUser(LoginUserInfo.getUserId())&&user.matchPassword(LoginUserInfo.getPassword())){
            if (matchUserByIdPw(LoginUserInfo, user)) {
                targetUser = user;
                flag = true;
                break;
            }
        }
        //사용자 인증이 됐다면 세션에 사용자 정보 저장
        if (flag) {
            session = req.getSession();
            session.setAttribute("user", targetUser);
            //메인 페이지로 리다이렉트
            resp.sendRedirect("/");
        }
        if (!flag) { //로그인 실패 시 loginFailed jsp 출력
            resp.sendRedirect("/user/loginFailed.jsp");
        }
    }

    boolean matchUserByIdPw(User LoginUserInfo, User user) {
        if (user.isSameUser(LoginUserInfo.getUserId()) && user.matchPassword(LoginUserInfo.getPassword()))
            return true;
        return false;
    }
}
