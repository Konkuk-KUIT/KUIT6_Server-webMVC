package jwp.controller;

import core.db.MemoryUserRepository;
import core.mvc.Controller;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 로그인 처리를 담당하는 컨트롤러
 */
public class LoginController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 사용자가 입력한 아이디와 비밀번호
        String id = request.getParameter("userId");
        String password = request.getParameter("password");

        User user = MemoryUserRepository.getInstance().findUserById(id);

        // 사용자가 제대로 비밀번호를 입력했는지 확인
        if(user != null && user.matchPassword(password)) {
            // 세션에 유저 정보 저장 후 메인 페이지로 리다이렉트
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            return "redirect:/";
        }

        // 로그인 실패 시 리다이렉트
        return "redirect:/user/loginFailed";
    }
}
