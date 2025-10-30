package jwp.controller;

import jwp.dao.UserDao;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginController implements Controller {
    @Override
   public String service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        UserDao userDao = new UserDao();
        User user = userDao.findByUserId(userId);

        if(user.getUserId() != null) {
            System.out.println("로그인 성공바뀐거인증");
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            return "redirect:/";
        }else{
            return "/loginFailed";
        }
    }
}
