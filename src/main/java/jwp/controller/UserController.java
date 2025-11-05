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
@RequestMapping("/user") // user로 시작되는 모든 요청 url이 클래스 안으로 들어오게 됨
@RequiredArgsConstructor
public class UserController {
    private final UserDao userDao;

    // 사용자가 회원가입 폼에서 데이터를 POST 방식으로 전송하면 이 메서드가 실행된다.
    @PostMapping("/signup")
    // 폼에 입력한 데이터를 자동으로 User 객체에 매핑해줌
    public String createUser(@ModelAttribute User user) {
        userDao.insert(user); // DB에 회원 정보 저장
        System.out.println("user 회원가입 완료");
        return "redirect:/user/list";
    }

    @GetMapping("/list")
    // Model : JSP로 데이터 전달용
    public String listUsers(HttpSession session, Model model) {
        if (UserSessionUtils.isLogined(session)) {
            model.addAttribute("users", userDao.findAll());
            return "user/list";
            // return "/user/list.jsp" -> Spring MVC에서 컨트롤러는 리턴하는 문자열을 가지고 어떤 파일을 보여줄지 판단..
        }
        return "redirect:/user/loginForm";
    }

    @PostMapping("/login")
    // @RequestParam : 클라이언트가 보낸 name=" " 값을 자동으로 매핑
    public String loginUser(@RequestParam String userId, @RequestParam String password,
                            HttpSession session) {
        User user = userDao.findByUserId(userId);
        if (user != null && user.isSameUser(new User(userId, password))) {
            session.setAttribute("user", user);
            return "redirect:/";
        }
        return "redirect:/user/loginFailed";
    }

    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }

    @PostMapping("/update")
    // @ModelAttribute : Spring이 자동으로 폼 데이터를 User 객체에 매핑. JSP로부터 데이터 수신용
    public String updateUser(@ModelAttribute User modifiedUser) {
        userDao.update(modifiedUser);
        return "redirect:/user/list";
    }

    @GetMapping("/updateForm")
    public String updateForm(@RequestParam String userId, HttpSession session) {
        User loginUser = (User) session.getAttribute("user");
        User user = userDao.findByUserId(userId);
        if (loginUser != null && user != null
                && user.getUserId().equals(loginUser.getUserId())) {// 수정되는 user와 수정하는 user가 동일한 경우
            return "user/updateForm";
        }
        return "redirect:/";
    }
}