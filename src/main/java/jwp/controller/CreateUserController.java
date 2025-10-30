package jwp.controller;

import jwp.dao.UserDao;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateUserController implements Controller {
    @Override
    public String service(HttpServletRequest req, HttpServletResponse resp) {
        User user = new User(req.getParameter("userId"),req.getParameter("password"),req.getParameter("name"),req.getParameter("email"));

        UserDao userDao = new UserDao();
        userDao.insert(user);

        System.out.println("user 회원가입 완료 바뀐거 인증");
        return "redirect:/";
    }
}
