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
public class UpdateUserController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(req.getParameter("userId"), req.getParameter("password"), req.getParameter("name"), req.getParameter("email"));
        MemoryUserRepository.getInstance().changeUserInfo(user);
        resp.sendRedirect("/user/list");
    }
}

//- **URL:** `/user/update`
//        - **Description:** 사용자 정보 수정. 요청 파라미터로 전달된 사용자 정보를 저장소에 업데이트한 후 사용자 목록 페이지로 리다이렉트.
//        - **Request Parameters:**
//        - `userId` (String)
//        - `password` (String)
//        - `name` (String)
//        - `email` (String)
//        - **Response:** `/user/list` 페이지로 리다이렉트
//- **비즈니스 로직:** `MemoryUserRepository` 싱글턴 저장소에서 해당 사용자 정보 업데이트
//- UpdateUserFormController는 해당 요청을 받아 /user/update.jsp로 userId를 바탕으로 사용자 정보를 알아와 전달하며 forward한다.
//e.g. `request.setAttribute(”users”,user)`