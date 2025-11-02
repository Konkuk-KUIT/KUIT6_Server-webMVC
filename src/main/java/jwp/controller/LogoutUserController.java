package jwp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutUserController implements Controller {
    @Override
    public String process(HttpServletRequest rq, HttpServletResponse resp) throws Exception {
        HttpSession session = rq.getSession();
        if(session != null){
            session.removeAttribute("user");
            System.out.println("user 로그아웃 성공");
        }
        return "redirect:/";
    }

}