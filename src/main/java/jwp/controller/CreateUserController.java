package jwp.controller;

import jwp.dao.UserDao;
import jwp.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 서블릿의 url을 ""로 지정한다. 이걸로 POST 요청 보내면 이 서블릿이 실행됨
public class CreateUserController implements Controller{
    @Override
    public String process(HttpServletRequest rq, HttpServletResponse resp) throws Exception {
        UserDao userDao = new UserDao();

        User user = new User(
                rq.getParameter("userId"),
                rq.getParameter("password"),
                rq.getParameter("name"),
                rq.getParameter("email")
        );

        userDao.insert(user);
        System.out.println("user 회원가입 완료");

        return "redirect:/user/list"; // 직접 경로 반환
    }

}


