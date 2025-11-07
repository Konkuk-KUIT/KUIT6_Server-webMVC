package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.dao.UserDao;
import jwp.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import util.SessionUtil;
import util.UserSessionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@Controller()
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

    @GetMapping("/list")
    public String listUsers(HttpSession session, Model model) throws Exception {

        if(UserSessionUtils.isLogined(session)) {
            model.addAttribute("users", userDao.findAll());
            return "/user/list";
        }
        return "redirect:/user/login";
    }

    @PostMapping("/login")
    public String userLogin(@RequestParam("userId") String userId, @RequestParam("password") String password, HttpSession session) throws Exception {
        User user = userDao.findByUserId(userId);
        if(user != null && user.getPassword().equals(password)){
            session.setAttribute("user", user);
            return "redirect:/";
        }
        return "redirect:/user/loginFailed";
    }

    @GetMapping("/logout")
    public String userLogout(HttpSession session) throws ServletException, IOException {
        session.removeAttribute("user");
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute User user) throws Exception {
        userDao.update(user);
        return "redirect:/user/list";
    }

    @GetMapping("/updateForm")
    public String updateForm(@RequestParam("userId") String userId , HttpSession session) throws ServletException, IOException {
        User user = (User)session.getAttribute("user");
        if (!UserSessionUtils.isLogined(session)) {
            return "redirect:/user/login";
        }
        if(user!=null && user.getUserId().equals(userId)){
            return "/user/updateForm";
        }
        return "redirect:/";
    }
}
