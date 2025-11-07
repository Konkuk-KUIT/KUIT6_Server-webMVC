package jwp.controller;

import core.web.SessionConst;
import jwp.dao.UserDao;
import jwp.model.User;
import jwp.web.UserSessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
        User loginUser = new User(userId,password);
        User user = userDao.findByUserId(userId);

        if(user!=null&&user.isSameUser(loginUser)){
            session.setAttribute("user",user);
            return "redirect:/";
        }
        return "redirect:/user/loginFailed";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) throws Exception {
        session.removeAttribute("user");
        return "redirect:/";
    }

    @GetMapping("/list")
    public String listUsers(HttpSession session, Model model) throws Exception {
        if(UserSessionUtils.isLogined(session)) {
            model.addAttribute("users", userDao.findAll());
            return "/user/list";
        }
        return "redirect:/user/loginForm";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user,HttpSession session) throws Exception {
        User loginUser = UserSessionUtils.getUserFromSession(session);
        if (loginUser == null || !loginUser.getUserId().equals(user.getUserId())) {
                        return "redirect:/user/loginForm";
        }
        userDao.update(user);
        return  "redirect:/user/list";
    }

    @GetMapping("/updateForm")
    public String updateUserForm(@RequestParam("userId") String requestedId,  HttpServletRequest request,
                                 Model model) throws Exception {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(SessionConst.USER_SESSION_KEY) == null) {
            return "redirect:/user/login.html";
        }

        User loginUser = (User) session.getAttribute(SessionConst.USER_SESSION_KEY);

        if (requestedId == null || !requestedId.equals(loginUser.getUserId())) {
            return "redirect:/";
        }

        model.addAttribute("user", loginUser);
        return "/user/updateForm";
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
