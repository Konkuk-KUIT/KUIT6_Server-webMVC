package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateUserController implements Controller {
    @Override
    public String process(HttpServletRequest rq, HttpServletResponse resp) throws Exception {
        HttpSession session = rq.getSession();
        String userId = rq.getParameter("userId");
        User settionUser = (User) session.getAttribute("user");

        // 세션 존재하고, 세션의 user와 수정하는 user가 같을때만 redirect
        if(settionUser!=null && userId.equals(settionUser.getUserId())){
            User updateUser = new User(userId,
                    rq.getParameter("password"),
                    rq.getParameter("name"),
                    rq.getParameter("email"));
            MemoryUserRepository.getInstance().changeUserInfo(updateUser);
            return "redirect:/user/list";
        }

        return "redirect:/";
    }

}

