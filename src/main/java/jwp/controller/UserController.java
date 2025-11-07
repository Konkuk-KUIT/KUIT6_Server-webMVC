package jwp.controller;

import jwp.dao.UserDao;
import jwp.model.User;
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

    /**
     * 회원가입 (CreateUserController 역할)
     */
    @GetMapping("/form")
    public String signUpForm() {
        return "user/form";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute User user) throws Exception {
        userDao.insert(user);
        return "redirect:/user/list";
    }

    /**
     * 회원 목록 (ListUserController 역할)
     */
    @GetMapping("/list")
    public String list(HttpSession session, Model model) throws Exception {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        model.addAttribute("users", userDao.findAll());
        return "user/list";
    }

    /**
     * 로그인 (LogInController 역할)
     */
    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("userId") String userId,
                        @RequestParam("password") String password,
                        HttpSession session) throws Exception {

        User user = userDao.findByUserID(userId);
        if (user != null && user.matchPassword(password)) {
            session.setAttribute("user", user);
            return "redirect:/";
        }
        return "redirect:/user/loginFailed";
    }

    @GetMapping("/loginFailed")
    public String loginFailed() {
        return "user/loginFailed";
    }

    /**
     * 로그아웃 (LogOutController 역할)
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }

    /**
     * 회원정보 수정 폼 (UpdateUserFormController 역할)
     */
    @GetMapping("/updateForm")
    public String updateForm(@RequestParam("userId") String userId,
                             HttpSession session, Model model) {

        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser != null && sessionUser.isSameUser(userId)) {
            model.addAttribute("user", sessionUser);
            return "user/updateForm";
        }
        return "redirect:/";
    }

    /**
     * 회원정보 수정 (UpdateUserController 역할)
     */
    @PostMapping("/update")
    public String update(@ModelAttribute User updatedUser, HttpSession session) throws Exception {
        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser != null) {
            // 강제로 세션의 사용자 식별자를 사용하여 영속 엔티티를 로드하고, 변경 가능한 필드만 갱신
            User persistent = userDao.findByUserID(sessionUser.getUserId());
            if (persistent != null) {
                persistent.update(updatedUser);
                userDao.update(persistent);
                return "redirect:/user/list";
            }
        }
        return "redirect:/";
    }
}