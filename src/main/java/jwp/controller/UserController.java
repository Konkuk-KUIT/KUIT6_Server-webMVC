package jwp.controller;

import jwp.dao.UserDao;
import jwp.model.User;
import jwp.util.UserSessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserDao userDao;

    @PostMapping("/signup")
    public String createUser(@ModelAttribute User user) throws Exception {
        userDao.insert(user);
        System.out.println("user 회원가입 완료");
        return "redirect:/user/list";
    }

    @PostMapping("/list")
    public String listUsers(HttpSession session, Model model) throws Exception {
        if (UserSessionUtils.isLogined(session)) {
            model.addAttribute("users", userDao.findAll());
            return "user/list";
        }
        return "redirect:/user/loginForm";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam String userId,
                             @RequestParam String password,
                             @RequestParam String name,
                             @RequestParam String email) throws Exception {

        User modifiedUser = new User(userId, password, name, email);
        userDao.update(modifiedUser);

        return "redirect:/user/list";
    }

    @GetMapping("/updateForm")
    public String showUpdateForm(@RequestParam String userId, HttpSession session) throws Exception {
        User user = userDao.findByUserId(userId);
        Object value = session.getAttribute("user");

        if (user != null && value != null && user.equals(value)) {
            return "user/updateForm";
        }
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@RequestParam String userId,
                        @RequestParam String password,
                        HttpSession session) throws Exception {

        User loginUser = new User(userId, password);
        User user = userDao.findByUserId(userId);

        if (user != null && user.isSameUser(loginUser)) {
            session.setAttribute("user", user);
            return "redirect:/";
        }
        return "redirect:/user/loginFailed";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }
}
