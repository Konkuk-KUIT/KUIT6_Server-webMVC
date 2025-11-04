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
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserDao userDao;

    @PostMapping("/signup")
    public String createUser(@ModelAttribute User user) throws Exception {
        userDao.insert(user);
        System.out.println("user 회원가입 완료");
        return "redirect:/user/list";
    }

    @PostMapping("/login")
    public String login(@RequestParam String userId, @RequestParam String password, HttpSession session) throws Exception {
        User loginUser = new User(userId, password);
        User user = userDao.findByUserId(userId);

        if(user != null && user.isSameUser(loginUser)) {
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

    @GetMapping("/list")
    public String execute(HttpSession session, Model model) throws Exception {
        if (UserSessionUtils.isLogined(session)) {
            model.addAttribute("users", userDao.findAll());
            return "user/list";
        }
        return "redirect:/user/loginForm";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user) throws Exception {
        userDao.update(user);
        return "redirect:/user/list";
    }

    @GetMapping("/updateForm")
    public String updateForm(@RequestParam String userId, HttpSession session) throws Exception {
        User user = userDao.findByUserId(userId);
        Object value = session.getAttribute("user");

        if (user != null && value != null) {
            if (user.equals(value)) {
                return "user/updateForm";
            }
        }
        return "redirect:/";
    }

    @GetMapping("/form")
    public String formForwarding() {
        return "user/form";
    }
    @GetMapping("/loginForm")
    public String loginFormForwarding() {
        return "user/login";
    }
    @GetMapping("/loginFailed")
    public String loginFailForwarding() {
        return "user/loginFailed";
    }
}
