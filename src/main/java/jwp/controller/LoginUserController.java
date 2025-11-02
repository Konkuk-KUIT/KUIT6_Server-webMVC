package jwp.controller;

import jwp.dao.UserDao;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginUserController implements Controller {
    private final UserDao userDao = new UserDao();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        User user = userDao.findByUserId(userId);
        if (user != null && user.getPassword().equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            System.out.println("user 로그인 성공");
            return "redirect:/";
        }

        System.out.println("로그인 실패");
        return "redirect:/user/loginFailed.jsp";
    }
}