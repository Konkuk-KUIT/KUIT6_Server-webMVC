package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.dao.UserDao;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CreateUserController implements Controller {
    private final UserDao userDao = new UserDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(
                req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email")
        );
        try {
            userDao.insert(user);
            System.out.println("user 회원가입 완료");
            return "redirect:/user/list";
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("데이터베이스 오류 발생: " + ex.getMessage());
            return "redirect:/user/form";
        }
    }
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        User user = new User(
//                req.getParameter("userId"),
//                req.getParameter("password"),
//                req.getParameter("name"),
//                req.getParameter("email")
//        );
//        MemoryUserRepository.getInstance().addUser(user);
//        System.out.println("user 회원가입 완료");
//        resp.sendRedirect("/user/list");
//    }
}
