package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;
import util.SessionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

// "/user/list"
public class ListUserController implements Controller {

    @Override
    public String handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(!SessionUtil.isLogin(request)){
            return "redirect:/user/login.jsp";
        }

        Collection<User> users = MemoryUserRepository.getInstance().findAll();
        request.setAttribute("users", users);
        return "/user/list.jsp";
    }

}
