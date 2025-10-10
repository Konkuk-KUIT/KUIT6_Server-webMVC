package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 서블릿의 url을 ""로 지정한다. 이걸로 POST 요청 보내면 이 서블릿이 실행됨
@WebServlet("/user/signup")
public class CreateUserController extends HttpServlet {
    @Override // 브라우저가 POST 방식으로 요청을 보냈을 때 실행되는 메서드.
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(req.getParameter("userId"), // 클라이언트가 보낸 입력값 꺼낸다
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email"));

        MemoryUserRepository.getInstance().addUser(user);
        System.out.println("user 회원가입 완료");
        resp.sendRedirect("/user/list"); // 다른 주소로 다시 요청 보내라고 명령
    }

}


