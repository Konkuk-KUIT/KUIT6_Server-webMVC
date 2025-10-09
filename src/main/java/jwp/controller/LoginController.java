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

@WebServlet("/user/login")
public class LoginController extends HttpServlet {
    private final MemoryUserRepository userRepository = MemoryUserRepository.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String userId = req.getParameter("userId");
       String password = req.getParameter("password");

       User user = userRepository.findUserById(userId);
       boolean ok = (user!=null)&&user.matchPassword(password);

       if(ok){
           HttpSession session = req.getSession();
           session.setAttribute("user",user);
           resp.sendRedirect("/");
       }else{
           resp.sendRedirect("/user/loginFailed.jsp");
       }
    }
}
