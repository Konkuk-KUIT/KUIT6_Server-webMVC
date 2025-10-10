package jwp.controller;

import core.db.MemoryUserRepository;
import core.mvc.Controller;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;

//@WebServlet("/user/login")
//public class LoginController extends HttpServlet {
public class LoginController implements Controller {
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        User LoginUserInfo = new User(req.getParameter("userId"), req.getParameter("password"), null, null);
//        User targetUser = null;
//        HttpSession session;
//        Collection<User> users = MemoryUserRepository.getInstance().findAll();
//
//        //사용자 인증
//        boolean flag = false;
//        for (User user : users) {
//            if (matchUserByIdPw(LoginUserInfo, user)) {
//                targetUser = user;
//                flag = true;
//                break;
//            }
//        }
//        //사용자 인증이 됐다면 세션에 사용자 정보 저장
//        if (flag) {
//            session = req.getSession();
//            session.setAttribute("user", targetUser);
//            //메인 페이지로 리다이렉트
//            resp.sendRedirect("/");
//        }
//        if (!flag) { //로그인 실패 시 loginFailed jsp 출력
//            resp.sendRedirect("/user/loginFailed.jsp");
//        }
//    }
//
//    boolean matchUserByIdPw(User LoginUserInfo, User user) {
//        if (user.isSameUser(LoginUserInfo.getUserId()) && user.matchPassword(LoginUserInfo.getPassword()))
//            return true;
//        return false;
//    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp)  {
        User LoginUserInfo = new User(req.getParameter("userId"), req.getParameter("password"), null, null);
        User targetUser = null;
        HttpSession session;
        Collection<User> users = MemoryUserRepository.getInstance().findAll();

        //사용자 인증
        boolean flag = false;
        for (User user : users) {
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
            return "redirect:/";
        }
        //로그인 실패 시 loginFailed jsp 출력
            return "redirect:/user/loginFailed";
    }

    boolean matchUserByIdPw(User LoginUserInfo, User user) {
        if (user.isSameUser(LoginUserInfo.getUserId()) && user.matchPassword(LoginUserInfo.getPassword()))
            return true;
        return false;
    }
}
