package jwp.controller;

import core.db.MemoryUserRepository;
import core.mvc.Controller;
import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;

//@WebServlet("/user/list")
//public class ListUserController extends HttpServlet {
public class ListUserController implements Controller {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Collection<User> users = MemoryUserRepository.getInstance().findAll();
//        HttpSession session = req.getSession();
//        Object value = session.getAttribute("user"); //현재 세션의 user 객체를 가져옴
//        User user = null;
//        if (value != null) {
//            //현재 session 의 유저가 실제 userrepo 에 저장된 user인지 확인
//            user = MemoryUserRepository.getInstance().findUserById(((User)value).getUserId());
//        }
//
//        //저장되어 있는 경우에만 접근 가능
//        if(user!=null){
//            req.setAttribute("users", users);
//            RequestDispatcher rd = req.getRequestDispatcher("/user/list.jsp");
//            rd.forward(req,resp);
//        }
//        resp.sendRedirect("/"); //로그인되지 않았다면 홈 화면
//    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp)  {
        Collection<User> users = MemoryUserRepository.getInstance().findAll();
        HttpSession session = req.getSession();
        Object value = session.getAttribute("user"); //현재 세션의 user 객체를 가져옴
        User user = null;
        if (value != null) {
            //현재 session 의 유저가 실제 userrepo 에 저장된 user인지 확인
            user = MemoryUserRepository.getInstance().findUserById(((User) value).getUserId());
        }

        //저장되어 있는 경우에만 접근 가능
        if (user != null) {
            req.setAttribute("users", users);
            return "/user/list";
        }
        return "redirect:/"; //로그인되지 않았다면 홈 화면
    }
}
