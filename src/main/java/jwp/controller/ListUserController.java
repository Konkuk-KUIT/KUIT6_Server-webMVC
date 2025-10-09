package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@WebServlet("/user/list")
public class ListUserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 세션을 가져와 로그인 상태를 확인합니다.
        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");

        // 2. 로그인 상태인 경우 (세션에 "user" 속성이 존재하는 경우)
        if (user != null) {
            // 기존 로직 수행: DB에서 사용자 목록을 가져와 포워딩합니다.
            Collection<User> users = MemoryUserRepository.getInstance().findAll();
            req.setAttribute("users", users);
            RequestDispatcher rd = req.getRequestDispatcher("/user/list.jsp");
            rd.forward(req, resp);
            return;
        }

        // 3. 로그인 상태가 아닌 경우, 메인 페이지로 리다이렉트합니다.
        resp.sendRedirect("/");
    }
}