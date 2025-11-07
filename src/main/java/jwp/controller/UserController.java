package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.dao.UserDao;
import jwp.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserDao userDao;

    //회원가입
    @PostMapping("/signup")
    public String createUser(@ModelAttribute User user) {
        MemoryUserRepository.getInstance().addUser(user);
        System.out.println("user 회원가입 완료");
        return "redirect:/";
    }

    //회원목록
    @GetMapping("/list")
    public String listUsers(HttpSession session, Model model) {
        Object value = session.getAttribute("user"); //현재 세션의 user 객체를 가져옴
        User user = null;
        if (value != null) {
            //현재 session 의 유저가 실제 userrepo 에 저장된 user인지 확인
            user = MemoryUserRepository.getInstance().findUserById(((User) value).getUserId());
        }

        //저장되어 있는 경우에만 접근 가능
        if (user != null) {
            model.addAttribute("users", userDao.findAll());
            return "/user/list";
        }
        return "redirect:/"; //로그인되지 않았다면 홈 화면
    }

    //로그인
    @PostMapping("/login")
    public String loginUser(HttpSession session, @RequestParam String userId, @RequestParam String password)  {
//        User LoginUserInfo = new User(req.getParameter("userId"), req.getParameter("password"), null, null);
        User LoginUserInfo = new User(userId, password, null, null);
        User targetUser = null;
//        HttpSession session;
        Collection<User> users = MemoryUserRepository.getInstance().findAll();

        //사용자 인증
        boolean flag = false;
        for (User user : users) {
            if (matchUserByIdPw(LoginUserInfo, user)) {
                targetUser = user;
                break;
            }
        }
        //사용자 인증이 됐다면 세션에 사용자 정보 저장
        if (targetUser != null) {
            session.setAttribute("user", targetUser);
            //메인 페이지로 리다이렉트
            return "redirect:/";
        }
        //로그인 실패 시 loginFailed jsp 출력
        return "redirect:/user/loginFailed";
    }

    boolean matchUserByIdPw(User LoginUserInfo, User user) {
        if (user.isSameUser(LoginUserInfo.getUserId()) && user.matchPassword(LoginUserInfo.getPassword()))
            return true;
        return false;
    }

    //로그아웃
    @PostMapping("/logout")
    public String logoutUser(HttpSession session) {
        //세션에서 사용자 정보 삭제
//        HttpSession session = req.getSession();
        session.removeAttribute("user");

        //메인 페이지로 리다이렉트
        return "redirect:/";
    }

    //회원정보 수정 form
    @GetMapping("/updateForm")
    String updateFormGet(HttpSession session, @RequestParam String userId) {
//        String id = req.getParameter("userId");
//        HttpSession session = req.getSession();
        User sessionUser = (User) session.getAttribute("user"); //session 에서 user객체 가져오기

        if(userId.equals(sessionUser.getUserId())){
            return "user/updateForm";
        }
        return "redirect:/";
    }

    @PostMapping("/updateForm")
    String updateFormPost(@RequestParam String userId, Model model){
//        User updatedUser = MemoryUserRepository.getInstance().findUserById(req.getParameter("userId"));
        User updatedUser = MemoryUserRepository.getInstance().findUserById(userId);
//        req.setAttribute("user", updatedUser);
        model.addAttribute("user", updatedUser);
        return "/user/updateForm";
    }

    //회원정보 수정
    @PostMapping("/update")
    public String update(@RequestParam String userId, @RequestParam String password, @RequestParam String name, @RequestParam String email)  {
        //새로운 User 객체 만든 후, update
        User updateUser = new User(userId, password, name, email);

        //그냥 단순히 새롭게 추가하는 듯?
        MemoryUserRepository.getInstance().changeUserInfo(updateUser);
        return "redirect:/user/list";
    }
}

