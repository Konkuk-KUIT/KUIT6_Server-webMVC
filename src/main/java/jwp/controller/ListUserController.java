// 기존: public class ListUserController extends HttpServlet
// 변경: public class ListUserController implements Controller
package jwp.controller;

import core.db.MemoryUserRepository;
import core.mvc.Controller; // Controller 인터페이스 import
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;

// @WebServlet 어노테이션 제거! DispatcherServlet이 대신 처리합니다.
public class ListUserController implements Controller {
    // doGet, doPost -> execute 메서드로 통합
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");

        if (user != null) {
            Collection<User> users = MemoryUserRepository.getInstance().findAll();
            req.setAttribute("users", users);
            // 직접 forward하지 않고, View 경로를 반환
            return "/user/list.jsp";
        }

        // 직접 redirect하지 않고, View 경로를 "redirect:" 접두사와 함께 반환
        return "redirect:/";
    }
}