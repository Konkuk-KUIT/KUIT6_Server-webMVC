package jwp.controller;

import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateFormController implements Controller {

    @Override
    public String executeGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        String userId = req.getParameter("userId");
        User currentUser = (User)session.getAttribute("user");

        if(currentUser != null && currentUser.getUserId().equals(userId)){
            //현재 사용자와 일치
            return "/user/updateForm.jsp";
        }
        //현재 사용자와 불일치
        return  "redirect:/";
    }
}
