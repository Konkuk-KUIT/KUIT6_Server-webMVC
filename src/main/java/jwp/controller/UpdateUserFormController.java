package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateUserFormController implements Controller{

    @Override
    public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemoryUserRepository memoryUserRepository = MemoryUserRepository.getInstance();
        String requestedId = req.getParameter("userId");
        User currentUser = (User) req.getSession().getAttribute("user");

        if(requestedId.equals(currentUser.getUserId())){
            req.setAttribute("user", currentUser);
            return "/user/updateForm.jsp";
        }

        return "redirect:/";
    }
}
