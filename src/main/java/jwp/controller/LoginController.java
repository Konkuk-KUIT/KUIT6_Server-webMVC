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
    //**Description:** 사용자 로그인 처리. 사용자 ID와 비밀번호를 받아 인증 후 세션에 사용자 정보 저장.
//  **Request Parameters:**
//            - `userId` (String) - 사용자 아이디
//            - `password` (String) - 비밀번호
//- **Response:**
//            - 로그인 성공 시 메인 페이지 `/` 로 리다이렉트
//            - 로그인 실패 시 `/user/loginFailed.jsp` 페이지로 리다이렉트
//- **세션 동작:** 성공 시 HttpSession 에 `user` 속성에 사용자 객체 저장
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User LoginUserInfo = new User(req.getParameter("userId"), req.getParameter("password"), null, null);
        User targetUser = null;
        MemoryUserRepository repo = MemoryUserRepository.getInstance();
        HttpSession session;


        //사용자 인증
        Boolean flag = false;
        for (User user : repo.findAll()) {
            if(user.isSameUser(LoginUserInfo.getUserId())&&user.matchPassword(LoginUserInfo.getPassword())){
                targetUser = user;
                flag=true;
                break;
            }
        }
        //사용자 인증이 됐다면 세션에 사용자 정보 저장
        if(flag) {
            session  = req.getSession();
            session.setAttribute("user", targetUser);
            //메인 페이지로 리다이렉트
            resp.sendRedirect("/");
        }
        if(!flag) { //로그인 실패 시 loginFailed jsp 출력
            resp.sendRedirect("/user/loginFailed.jsp");
        }
    }
}
