package jwp.controller;

import core.mvc.Controller;
import jwp.dao.UserDao;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginUserController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User user = new UserDao().findByUserID(req.getParameter("userId"));

        // 사용자가 존재하고 비밀번호가 일치하면
        if (user != null && user.matchPassword(req.getParameter("password"))) {
            // 세션에 사용자 정보 저장
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            // 메인 페이지로 리다이렉트
            return "redirect:/";
        }

        // 로그인 실패 시
        return "redirect:/user/loginFailed.jsp";
    }
}