package jwp.controller;

import core.db.MemoryUserRepository;
import core.mvc.Controller;
import jwp.dao.UserDao;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateUserController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // POST 요청 시 한글 깨짐 방지
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        User sessionUser = (User) session.getAttribute("user");
        String userId = req.getParameter("userId");

        // 세션 정보와 수정할 사용자 정보가 일치하는지 권한 확인
        if (sessionUser != null && sessionUser.isSameUser(userId)) {
            User updatedUser = new User(
                    userId,
                    req.getParameter("password"),
                    req.getParameter("name"),
                    req.getParameter("email"));

            new UserDao().update(updatedUser);

            // 수정 후 사용자 목록 페이지로 리다이렉트
            return "redirect:/user/list";
        }

        // 권한이 없으면 메인 페이지로 리다이렉트
        return "redirect:/";
    }
}