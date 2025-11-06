package jwp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForwardController {

    @GetMapping("/user/form")
    public String userForm() {
        return "/user/form";
    }

    @GetMapping("/user/loginForm")
    public String loginForm() {
        return "/user/login";
    }

    @GetMapping("/user/loginFailed")
    public String loginFailed() {
        return "/user/loginFailed";
    }
}
