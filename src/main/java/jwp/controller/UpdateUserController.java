package jwp.controller;

import core.db.MemoryUserRepository;
import core.mvc.Controller;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 사용자 정보 수정 처리를 담당하는 컨트롤러
 */
public class UpdateUserController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("userId");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        // 비즈니스 로직: MemoryUserRepository 싱글턴 저장소에서 해당 사용자 정보 업데이트
        User user = MemoryUserRepository.getInstance().findUserById(id);
        User updateUser = new User(id, password, name, email);
        user.update(updateUser);

        // 세션의 사용자 정보도 업데이트
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        System.out.println("user 정보 수정 완료");

        // 수정 완료 후 사용자 목록으로 리다이렉트
        return "redirect:/user/list";
    }
}
