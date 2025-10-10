package jwp.frontcontroller;

import jwp.controller.Controller;
import jwp.controller.CreateUserController;
import jwp.controller.HomeController;
import jwp.controller.SignInController;

import java.util.HashMap;
import java.util.Map;

public class MappingController {
    private Map<String, Controller> controllers;

    public MappingController() {
        this.controllers = new HashMap<>();
        controllers.put("/", new HomeController());
        controllers.put("/user/signup", new CreateUserController());
        controllers.put("/user/login", new SignInController());
    }

    public Controller getController(String url) {
        return controllers.get(url);
    }
}
