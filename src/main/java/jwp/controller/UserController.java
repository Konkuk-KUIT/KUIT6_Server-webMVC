package jwp.controller;

import jwp.dao.UserDao;
import jwp.model.User;
import jwp.util.UserSessionUtils;
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
    public String createUser(@ModelAttribute User user) throws Exception {

        if(userDao.findByUserId(user.getUserId()) != null)
            throw new IllegalArgumentException("이미 회원가입된 ID입니다.");

        userDao.insert(user);
        System.out.println("user 회원가입 완료");
        return "redirect:/user/list";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute User loginUser, HttpSession session) throws Exception {

        User user = userDao.findByUserId(loginUser.getUserId());

        if (user != null && user.isSameUser(loginUser)) {
            session.setAttribute("user", user);
            return "redirect:/";
        }
        return "redirect:/user/loginFailed";
    }

    @GetMapping("/logout")
    public String execute(HttpSession session) throws Exception {
        session.removeAttribute("user");
        return "redirect:/";
    }

    @GetMapping("/list")
    public String listUsers(HttpSession session, Model model) throws Exception {
        if (UserSessionUtils.isLogined(session)) {
            model.addAttribute("users", userDao.findAll());
            return "user/list";
        }
        return "redirect:/user/loginForm";
    }

    @GetMapping("/updateForm")
    public String updateForm(@RequestParam String userId, HttpSession session, Model model) throws Exception {

        User user = userDao.findByUserId(userId);
        Object value = session.getAttribute("user");

        if (user != null && value != null) {
            if (user.equals(value)) {            // 수정되는 user와 수정하는 user가 동일한 경우
                model.addAttribute("user", user); //req에 setAttribute()하는 대신, model에 추가
                return "user/updateForm";
            }
        }
        return "redirect:/";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute User modifiedUser) {
        userDao.update(modifiedUser);
        return "redirect:/user/list";
    }

    @GetMapping("/form")
    public String showForm() {
        return "user/form";
    }
    
    @GetMapping("/login")
    public String showLoginForm() {
        return "user/login";
    }
    
    @GetMapping("/loginFailed")
    public String showLoginFailed() {
        return "user/loginFailed";
    }
}
