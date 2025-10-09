package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/update")
public class UpdateUserController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 한글 깨짐 방지를 위한 인코딩 설정
        req.setCharacterEncoding("UTF-8");

        // 1. 현재 세션에서 로그인된 사용자 정보를 가져옵니다.
        HttpSession session = req.getSession();
        User sessionUser = (User) session.getAttribute("user");

        // 2. 요청 파라미터(수정할 정보)를 가져옵니다.
        String userId = req.getParameter("userId");

        // 3. 권한 검증: 세션이 존재하고, 세션의 사용자가 수정하려는 사용자와 동일한지 확인
        if (sessionUser != null && sessionUser.isSameUser(userId)) {
            // 4. 검증 성공: 파라미터로 받은 정보로 새로운 User 객체를 생성
            User updatedUser = new User(
                    userId,
                    req.getParameter("password"),
                    req.getParameter("name"),
                    req.getParameter("email"));

            // 5. 저장소(DB)의 사용자 정보를 업데이트
            MemoryUserRepository.getInstance().changeUserInfo(updatedUser);

            // 6. 사용자 목록 페이지로 리다이렉트
            resp.sendRedirect("/user/list");
            return;
        }

        // 7. 검증 실패: 권한이 없으므로 메인 페이지로 리다이렉트
        resp.sendRedirect("/");
    }
}