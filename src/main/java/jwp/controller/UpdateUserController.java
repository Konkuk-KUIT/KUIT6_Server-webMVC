package jwp.controller;

import jwp.dao.UserDao;
import jwp.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UpdateUserController {

    private final UserDao userDao;

    @PostMapping("/user/update")
    public String updateUser(User user) {
        userDao.update(user);
        return "redirect:/user/list";
    }
}
