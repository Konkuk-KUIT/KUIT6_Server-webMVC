package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class SignInController implements Controller {
    @Override
    public String executePost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userId = req.getParameter("userId");
        String pwd = req.getParameter("password");
        User user = MemoryUserRepository.getInstance().findUserById(userId);
        if(user != null && pwd.equals(user.getPassword())) {
            // 로그인 성공
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            return "redirect:/";
        }
        // 로그인 실패
        return "redirect:/user/loginFailed.jsp";
    }
}
