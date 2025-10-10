package jwp.controller;

import java.util.HashMap;
import java.util.Map;

public class RequestMapper {
    private Map<String, Controller> controllerMap = new HashMap<>();

    public void initControllerMap() {
        controllerMap.put("/", new HomeController());
        controllerMap.put("/user/signup", new CreateUserController());
        controllerMap.put("/user/list", new ListUserController());
        controllerMap.put("/user/login", new LoginUserController());
        controllerMap.put("/user/logout", new LogoutUserController());
        controllerMap.put("/user/update", new UpdateUserController());
        controllerMap.put("/user/updateForm", new UpdateUserFormController());
    }

    public Controller findController(String url) {
        return controllerMap.get(url
        );
    }
}
