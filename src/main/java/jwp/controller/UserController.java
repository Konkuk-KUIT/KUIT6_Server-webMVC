package jwp.controller;

import jwp.dao.UserDao;
import jwp.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserDao userDao;

    @PostMapping("/signup")
    public String createUser(@ModelAttribute User user) {
        userDao.insert(user);
        System.out.println("user 회원가입 완료!");
        return "redirect:" + "/user/list";
    }

    @GetMapping("/list")
    public String listUsers(HttpSession session, Model model) {
        if ((User) session.getAttribute("user") == null) {
            return "redirect:" + "/user/login.jsp";
        }
        model.addAttribute("users", userDao.findAll());
        return "user/list";
    }

    @GetMapping("/updateForm")
    public String updateForm(HttpSession session) {
        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser != null) {
            return "user/updateForm";
        }
        return "redirect:" + "/";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user) {
        User oldUser = userDao.findByUserId(user.getUserId());
        if (oldUser == null) return "redirect:" + "user/login";
        oldUser.update(user);
        userDao.update(oldUser);
        return "redirect:" + "/user/list";
    }

    @PostMapping("/login")
    public String login(@RequestParam String userId, @RequestParam String password, HttpSession session){
        User user = userDao.findByUserId(userId);
        if (user != null && user.matchPassword(password)) {
            session.setAttribute("user", user);
            return "redirect:" + "/";
        }
        return "redirect:" + "/user/loginFailed.jsp";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:" + "/";
    }
}
