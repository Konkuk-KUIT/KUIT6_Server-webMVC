package jwp.controller;

import jwp.dao.UserDao;
import jwp.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class UpdateUserFormController {

    private final UserDao userDao;

    @GetMapping("/user/updateForm")
    public String showUpdateForm(String userId, HttpSession session) {
        User user = userDao.findByUserId(userId);
        Object value = session.getAttribute("user");

        if (user != null && value != null) {
            if (user.equals(value)) {  // 수정되는 user와 수정하는 user가 동일한 경우
                return "/user/updateForm";
            }
        }
        return "redirect:/";
    }
}