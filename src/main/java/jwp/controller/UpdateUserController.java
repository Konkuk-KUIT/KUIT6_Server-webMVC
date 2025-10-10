package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateUserController extends HttpServlet implements Controller{

    @Override
    public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //UpdateUserController에서 req에 인자를 담아 전달할 수 있음
        User updateUser = new User(req.getParameter("userId"), req.getParameter("password"), req.getParameter("name"), req.getParameter("email"));

        MemoryUserRepository.getInstance().changeUserInfo(updateUser);

        req.getSession().setAttribute("user", updateUser);

        return "redirect:/user/list";
    }
}
