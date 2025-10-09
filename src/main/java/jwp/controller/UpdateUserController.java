package jwp.controller;

import core.db.MemoryUserRepository;
import core.web.SessionConst;
import core.web.mvc.Controller;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class UpdateUserController implements Controller {
    private final MemoryUserRepository memoryUserRepository = MemoryUserRepository.getInstance();

    @Override
    public String handle(HttpServletRequest req, HttpServletResponse resp, Map<String, Object> model) throws ServletException, IOException {
        if (!"POST".equalsIgnoreCase(req.getMethod())) {
            return "redirect:/";
        }

        HttpSession session = req.getSession(false);
        Object attr = (session != null) ? session.getAttribute(SessionConst.USER_SESSION_KEY) : null;
        if (!(attr instanceof User loginUser)) {
            return "redirect:/user/login.html";
        }

        String userId = req.getParameter("userId");
        if(userId==null||!userId.equals(loginUser.getUserId())){
            return "redirect:/";
        }

        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        User updateUser = new User(userId,password,name,email);
        memoryUserRepository.changeUserInfo(updateUser);
        session.setAttribute(SessionConst.USER_SESSION_KEY, updateUser);

        return "redirect:/user/list";
    }
}
