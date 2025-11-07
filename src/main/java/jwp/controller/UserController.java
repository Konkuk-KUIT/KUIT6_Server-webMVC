package jwp.controller;

import jwp.dao.UserDao;
import jwp.enums.HttpUrl;
import jwp.enums.JspUrl;
import jwp.enums.ReqAttribute;
import jwp.enums.UserParameter;
import jwp.model.User;
import jwp.util.UserSessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserDao userDao;

    @PostMapping("/signup")
    public String createUser(@ModelAttribute User user){
        userDao.insert(user);
        return HttpUrl.LIST.redirect();
    }

    @GetMapping("/list")
    public String listUsers(HttpSession session, Model model){
        if(!UserSessionUtils.isLogined(session)) {
            return HttpUrl.ROOT.redirect();
        }
        model.addAttribute("users", userDao.findAll());
        return JspUrl.LIST.getValue();
    }

    @GetMapping("/updateForm")
    public String updateForm(@RequestParam("userId") String userId, HttpSession session){
        if(!UserSessionUtils.isLogined(session) || userId != session.getAttribute("userId"))
            return HttpUrl.ROOT.redirect();
        return JspUrl.UPDATE_FORM.getValue();
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user){
        userDao.update(user);
        return HttpUrl.LIST.redirect();
    }

    @GetMapping("/login")
    public String loginForm() { return "user/login"; }

    @PostMapping("/login")
    public String loginUser(@RequestParam("userId") String userId, @RequestParam("password") String password, HttpSession session) throws SQLException {
        User user = userDao.findByUserId(userId);
        if (user == null || !user.matchPassword(password)) {
            return JspUrl.LOGIN_FAIL.getValue();
        }

        session.setAttribute(ReqAttribute.USER.getValue(), user);
        return HttpUrl.ROOT.redirect();
    }

    @GetMapping("/logout")
    public String logoutUser(HttpSession session){
        session.removeAttribute(ReqAttribute.USER.getValue());
        return HttpUrl.ROOT.redirect();
    }
}
