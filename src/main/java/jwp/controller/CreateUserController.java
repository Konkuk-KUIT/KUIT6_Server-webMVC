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
public class CreateUserController implements Controller{
    @Override
    public String process(HttpServletRequest rq, HttpServletResponse resp) throws Exception {
        User user = new User(rq.getParameter("userId"), // 클라이언트가 보낸 입력값 꺼낸다
                rq.getParameter("password"),
                rq.getParameter("name"),
                rq.getParameter("email"));
        MemoryUserRepository.getInstance().addUser(user);
        System.out.println("user 회원가입 완료");

        return "redirect:/user/list"; // 직접 경로 반환
    }

}


