package jwp.controller;

import jwp.dao.UserDao;
import jwp.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
