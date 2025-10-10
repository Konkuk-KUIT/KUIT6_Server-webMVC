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
import java.util.Objects;

@WebServlet("/user/updateForm")
public class UpdateUserFormController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return "";
    }
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        User sessionUser = (User) session.getAttribute("user");
//        String updateUserId = req.getParameter("userId");
//
//        if (sessionUser != null && sessionUser.isSameUser(updateUserId)) {
//            User user = MemoryUserRepository.getInstance().findUserById(updateUserId);
//            req.setAttribute("user", user);
//            RequestDispatcher rd = req.getRequestDispatcher("/user/updateForm.jsp");
//            rd.forward(req, resp);
//        } else {
//            resp.sendRedirect("/");
//        }
//    }
}

//- 수정하려는 사용자 아이디와 현재 세션의 사용자 아이디가 동일하면 `/user/updateForm.jsp` 로 포워드
//- 그렇지 않으면 메인 페이지 `/` 로 리다이렉트
