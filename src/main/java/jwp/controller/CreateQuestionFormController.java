package jwp.controller;

import jwp.dao.UserDao;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CreateQuestionFormController implements Controller {
    @Override
    public String service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        UserDao userDao = new UserDao();

        if(user != null && userDao.findByUserId(user.getUserId())!=null) {
            System.out.println("자 제가 한게 맞습니다.");
            return "/qna/form";
        }
        System.out.println("아닙니다 넌 틀렸습니다.");

        return "redirect:/";
    }
}
