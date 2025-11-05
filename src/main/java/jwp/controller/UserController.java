package jwp.controller;

import jwp.dao.UserDao;
import jwp.model.User;
import jwp.util.UserSessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user") // user로 시작되는 모든 요청 url이 클래스 안으로 들어오게 됨
@RequiredArgsConstructor
public class UserController {
    private final UserDao userDao;

    // 사용자가 회원가입 폼에서 데이터를 POST 방식으로 전송하면 이 메서드가 실행된다.
    @PostMapping("/signup")
                                // 폼에 입력한 데이터를 자동으로 User 객체에 매핑해줌
    public String createUser(@ModelAttribute User user) throws Exception {
        userDao.insert(user); // DB에 회원 정보 저장
        System.out.println("user 회원가입 완료");
        return "redirect:/user/list";
    }

    @GetMapping("/list")
    public String listUsers(HttpSession session, Model model) throws Exception {
        if (UserSessionUtils.isLogined(session)) {
            model.addAttribute("users", userDao.findAll());
            return "user/list";
            // return "/user/list.jsp" -> Spring MVC에서 컨트롤러는 리턴하는 문자열을 가지고 어떤 파일을 보여줄지 판단..
        }
        return "redirect:/user/loginForm";
    }
}