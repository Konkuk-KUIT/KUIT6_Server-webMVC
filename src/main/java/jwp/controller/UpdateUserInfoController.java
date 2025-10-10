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
public class UpdateUserInfoController extends HttpServlet {

//- **Description:** 사용자 정보 수정 폼 요청.
//- **Request Parameters:**
//            - `userId` (String) - 수정하고자 하는 사용자 아이디
//- **Response:**
//            - 수정하려는 사용자 아이디와 현재 세션의 사용자 아이디가 동일하면 `/user/updateForm.jsp` 로 포워드
//            - 그렇지 않으면 메인 페이지 `/` 로 리다이렉트
//- **권한 검증:** 세션 내 `user` 객체와 요청된 `userId` 가 동일해야 함

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("userId");
        HttpSession session = req.getSession();
        User sessionUser = (User) session.getAttribute("user"); //session 에서 user객체 가져오기


        if(id.equals(sessionUser.getUserId())){
            RequestDispatcher rd = req.getRequestDispatcher("/user/updateForm.jsp");
            rd.forward(req, resp);
        }
        resp.sendRedirect("/");
    }

}
