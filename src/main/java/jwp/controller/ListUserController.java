package jwp.controller;

import jwp.dao.UserDao;
import jwp.util.UserSessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class ListUserController {

    private final UserDao userDao;

    @GetMapping("/user/list")
    public String listUsers(Model model, HttpSession session) {
        if (UserSessionUtils.isLogined(session)) {
            model.addAttribute("users", userDao.findAll());
            return "/user/list";
        }
        return "redirect:/user/loginForm";
    }
}