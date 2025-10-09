package jwp.controller;

import core.mvc.Controller;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateUserFormController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String userIdToUpdate = req.getParameter("userId");
        HttpSession session = req.getSession();
        User sessionUser = (User) session.getAttribute("user");

        // 세션 정보와 수정할 사용자 정보가 일치하는지 권한 확인
        if (sessionUser != null && sessionUser.isSameUser(userIdToUpdate)) {
            // 일치하면 updateForm.jsp로 포워드
            req.setAttribute("user", sessionUser);
            return "/user/updateForm.jsp";
        }

        // 권한이 없으면 메인 페이지로 리다이렉트
        return "redirect:/";
    }
}