package jwp.controller;

import jwp.dao.UserDao;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateUserController implements Controller {
    private final UserDao userDao = new UserDao();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        String userId = req.getParameter("userId");
        User sessionUser = (User) session.getAttribute("user");

        if (sessionUser != null && userId.equals(sessionUser.getUserId())) {
            User updatedUser = new User(
                    userId,
                    req.getParameter("password"),
                    req.getParameter("name"),
                    req.getParameter("email")
            );

            userDao.update(updatedUser);

            return "redirect:/user/list";
        }

        return "redirect:/";
    }
}