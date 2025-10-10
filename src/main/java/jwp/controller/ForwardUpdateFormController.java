package jwp.controller;

import core.db.MemoryUserRepository;
import core.mvc.Controller;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 사용자 정보 수정 폼을 보여주는 컨트롤러
 */
public class ForwardUpdateFormController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("userId");
        User user = MemoryUserRepository.getInstance().findUserById(id);

        // To Do : 수정하려는 사용자 아이디와 현재 세션의 사용자 아이디가 동일한지 확인
        request.setAttribute("user", user);

        return "/user/updateForm";
    }
}
