package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/updateForm")
public class UpdateUserController extends HttpServlet {

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("userId");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        // 비즈니스 로직: MemoryUserRepository 싱글턴 저장소에서 해당 사용자 정보 업데이트
        User user = MemoryUserRepository.getInstance().findUserById(id);
        User updateUser = new User(id, password, name, email);
        user.update(updateUser);

        resp.sendRedirect("/user/updateForm.jsp");
    }
}
