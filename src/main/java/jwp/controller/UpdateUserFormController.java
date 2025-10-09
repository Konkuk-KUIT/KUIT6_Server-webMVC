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

@WebServlet("/user/updateForm")
public class UpdateUserFormController extends HttpServlet {
    private final MemoryUserRepository userRepository = MemoryUserRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String requestedId = req.getParameter("userId");

       HttpSession session = req.getSession(false);
       if(session==null||session.getAttribute("user")==null){
           resp.sendRedirect("/user/login.html");
           return;
       }

       User loginUser = (User)session.getAttribute("user");

       if(requestedId==null||!requestedId.equals(loginUser.getUserId())){
           resp.sendRedirect("/");
           return;
       }

       req.setAttribute("user",loginUser);
       req.getRequestDispatcher("/user/updateForm.jsp").forward(req, resp);

    }
}
