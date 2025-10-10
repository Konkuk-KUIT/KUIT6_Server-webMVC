package jwp.frontcontroller;

import jwp.controller.*;

import java.util.HashMap;
import java.util.Map;

public class MappingController {
    private Map<String, Controller> controllers;

    public MappingController() {
        this.controllers = new HashMap<>();
        controllers.put("/", new HomeController());
        controllers.put("/user/signup", new CreateUserController());
        controllers.put("/user/login", new SignInController());
        controllers.put("/user/logout", new SignOutController());
        controllers.put("/user/list", new ListUserController());
    }

    public Controller getController(String url) {
        return controllers.get(url);
    }
}
