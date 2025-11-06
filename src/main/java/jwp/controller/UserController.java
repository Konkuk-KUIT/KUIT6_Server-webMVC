package jwp.controller;

import jwp.dao.UserDao;
import jwp.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserDao userDao;

    // 회원가입
    @PostMapping("/signup")
    public String createUser(@ModelAttribute User user) throws Exception {
        userDao.insert(user);
        System.out.println("user 회원가입 완료");
        return "redirect:/user/list";
    }

    // 로그인
    @PostMapping("/login")
    public String loginUser(
            @RequestParam("userId") String userId,
            @RequestParam("password") String password,
            HttpSession session
    ) throws Exception
    {
        User user = userDao.findByUserId(userId);
        if (user == null) {
            System.out.println("아이디를 확인해주세요");
            return "redirect:/user/loginFailed";
        }
        if (user.matchPassword(password)) {
            session.setAttribute("user", user);
            System.out.println("로그인 성공");
            return "redirect:/";
        } else {
            System.out.println("비밀번호를 확인해주세요");
            return "redirect:/user/loginFailed";
        }
    }

    // 유저 전체 목록 조회
    @GetMapping("/list")
    public String listUsers(HttpSession session, Model model) throws SQLException {
        if (UserSessionUtils.isLogined(session)) {
            model.addAttribute("users", userDao.findAll());
            return "user/list";
        }
        return "redirect:/user/loginForm";
    }
}
