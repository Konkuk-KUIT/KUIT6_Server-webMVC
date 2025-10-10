package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/update")
public class UpdateUserFormController extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("userId");
        User user = MemoryUserRepository.getInstance().findUserById(id);

       // To Do : 수정하려는 사용자 아이디와 현재 세션의 사용자 아이디가 동일한지 확인
        req.setAttribute("user", user);

        resp.sendRedirect("/user/updateForm");

    }
}
