package jwp.controller;

import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/updateForm")
public class UpdateUserFormController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 요청 파라미터에서 수정 대상의 userId를 가져옵니다.
        String userIdToUpdate = req.getParameter("userId");

        // 2. 현재 세션을 가져와 로그인된 사용자 정보를 확인합니다.
        HttpSession session = req.getSession();
        User sessionUser = (User) session.getAttribute("user");

        // 3. 권한을 검증합니다. (세션에 사용자가 있고, 세션 ID와 요청 ID가 일치하는가?)
        if (sessionUser != null && sessionUser.isSameUser(userIdToUpdate)) {
            // 4. 검증 성공: /user/updateForm.jsp로 포워드합니다.
            // JSP에서 사용자 정보를 쉽게 사용할 수 있도록 request에 담아줍니다.
            req.setAttribute("user", sessionUser);
            RequestDispatcher rd = req.getRequestDispatcher("/user/updateForm.jsp");
            rd.forward(req, resp);
            return;
        }

        // 5. 검증 실패: 메인 페이지로 리다이렉트합니다.
        resp.sendRedirect("/");
    }
}