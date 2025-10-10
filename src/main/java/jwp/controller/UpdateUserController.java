package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/update")
public class UpdateUserController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String userId = req.getParameter("userId");
        User settionUser = (User) session.getAttribute("user");

        // 세션 존재하고, 세션의 user와 수정하는 user가 같을때만 redirect
        if(settionUser!=null && userId.equals(settionUser.getUserId())){
            User updateUser = new User(userId,
                    req.getParameter("password"),
                    req.getParameter("name"),
                    req.getParameter("email"));
            MemoryUserRepository.getInstance().changeUserInfo(updateUser);
            resp.sendRedirect("/user/list");
        }
    }
}

