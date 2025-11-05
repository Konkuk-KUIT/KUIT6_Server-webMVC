package jwp.controller;

import jwp.dao.UserDao;
import jwp.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserDao userDao;

    @PostMapping("/signup")
    public String createUser(@ModelAttribute User user) {
        userDao.insert(user);
        System.out.println("user 회원가입 완료 바뀐거 인증");
        return "redirect:/";
    }

    @GetMapping("/list")
    public String listUsers(HttpSession session, Model model){
        model.addAttribute("users",userDao.findAll());
        User currentUser = (User) session.getAttribute("user");
        if(currentUser == null) {
            return "redirect:/";
        }
        else{
            return "user/list";
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam String userId, Model model, HttpSession session) {
        User user = userDao.findByUserId(userId);
        if(user.getUserId() != null) {
            System.out.println("로그인 성공바뀐거인증");
            session.setAttribute("user", user);
            model.addAttribute("user", user);
            return "redirect:/";
        }else{
            return "loginFailed";
        }
    }
    @GetMapping("/logout")
    public String logout(SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute User user) {
        userDao.update(user);

        return "redirect:/user/list";
    }

    @GetMapping("/updateForm")
    public String updateForm(@RequestParam String userId, HttpSession session)  {
        User user = (User) session.getAttribute("user");
        if(userId != null && userId.equals(user.getUserId())) {
            return "user/updateForm";
        }else {
            return "redirect:/";
        }
    }


}
