package jwp.controller;

import core.db.MemoryUserRepository;
import core.mvc.Controller;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("/user/updateForm")
//public class UpdateUserFormController extends HttpServlet {
public class UpdateUserFormController implements Controller {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String id = req.getParameter("userId");
//        HttpSession session = req.getSession();
//        User sessionUser = (User) session.getAttribute("user"); //session 에서 user객체 가져오기
//
//        if(id.equals(sessionUser.getUserId())){
//            RequestDispatcher rd = req.getRequestDispatcher("/user/updateForm.jsp");
//            rd.forward(req, resp);
//        }
//        resp.sendRedirect("/");
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        User updatedUser = MemoryUserRepository.getInstance().findUserById(req.getParameter("userId"));
//        req.setAttribute("user", updatedUser);
//        RequestDispatcher rd = req.getRequestDispatcher("/user/updateForm.jsp");
//        rd.forward(req, resp);
//    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String method = req.getMethod();
        if(method.equals("POST"))
            return executeGet(req, resp);
        if(method.equals("GET"))
            return executePost(req, resp);
        return ""; //Todo: ?? 뭘 return?
    }

    String executeGet(HttpServletRequest req, HttpServletResponse resp) {
                String id = req.getParameter("userId");
        HttpSession session = req.getSession();
        User sessionUser = (User) session.getAttribute("user"); //session 에서 user객체 가져오기

        if(id.equals(sessionUser.getUserId())){
            return "user/updateForm";
        }
        return "redirect:/";
    }

    String executePost(HttpServletRequest req, HttpServletResponse resp){
        User updatedUser = MemoryUserRepository.getInstance().findUserById(req.getParameter("userId"));
        req.setAttribute("user", updatedUser);
        return "/user/updateForm";
    }

}
