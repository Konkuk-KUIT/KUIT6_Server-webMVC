// 기존: public class CreateUserController extends HttpServlet
// 변경: public class CreateUserController implements Controller
package jwp.controller;

import core.mvc.Controller; // Controller 인터페이스 import
import jwp.dao.UserDao;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// @WebServlet 어노테이션 제거
public class CreateUserController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User user = new User(req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email"));
        new UserDao().insert(user);
        System.out.println("user 회원가입 완료");

        // 직접 redirect하지 않고, View 경로를 반환
        return "redirect:/user/list";
    }
}