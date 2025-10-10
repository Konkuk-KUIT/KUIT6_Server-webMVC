package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginUserController implements Controller {
    @Override
    public String process(HttpServletRequest rq, HttpServletResponse resp) throws Exception {
        String userId = rq.getParameter("userId");
        String password = rq.getParameter("password");
        User user = MemoryUserRepository.getInstance().findUserById(userId);

        if (user != null && user.matchPassword(password)) {
            HttpSession session = rq.getSession();
            session.setAttribute("user", user);
            System.out.println("user 로그인 성공");
            return "redirect:/";
        }
        return "redirect:/user/loginFailed.jsp";
    }

}
