package jwp.controller;

import core.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 로그인 실패 페이지를 보여주는 컨트롤러
 */
public class ForwardLoginFailedController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/user/loginFailed";
    }
}
