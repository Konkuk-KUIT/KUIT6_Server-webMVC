package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.dao.QuestionDao;
import jwp.dao.UserDao;
import jwp.model.User;
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

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserDao userDao;
    private final QuestionDao questionDao;

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

    // 로그아웃
    @PostMapping("/logout")
    public String logoutUser(HttpSession session)  {
        if (session.getAttribute("user") != null) {
            session.removeAttribute("user");
        }
        System.out.println("로그아웃 되었습니다");
        return "redirect:/";
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

    // UpdateUserForm
    @GetMapping("/updateForm")
    public String showUpdateForm(HttpSession session, @RequestParam("userId")  String updateUserId, Model model) throws Exception {
        User sessionUser = (User) session.getAttribute("user");

        if (sessionUser != null && sessionUser.isSameUser(updateUserId)) {
            User user = userDao.findByUserId(updateUserId);
            model.addAttribute("user", user);
            return "user/updateForm";
        } else {
            return "redirect:/";
        }
    }


    // 폼(view)을 보여주는 메소드들
    @GetMapping("/signupForm")
    public String showSignupForm() {
        return "user/form";
    }
    @GetMapping("/loginForm")
    public String showLoginForm() {
        return "user/login";
    }
    @GetMapping("/loginFailed")
    public String showLoginFailed() {
        return "user/loginFailed";
    }
}
