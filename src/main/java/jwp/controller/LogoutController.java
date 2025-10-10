package jwp.controller;

import core.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 로그아웃 처리를 담당하는 컨트롤러
 */
public class LogoutController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 세션에서 사용자 정보를 삭제하고 (/) 로 리다이렉트
        HttpSession session = request.getSession();
        session.removeAttribute("user");

        return "redirect:/";
    }
}
