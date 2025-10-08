package jwp.controller;

import java.util.HashMap;
import java.util.Map;

public class RequestMapper {
    private static final Map<String, Controller> controllers = new HashMap<String, Controller>();

    static {
        controllers.put("/", new HomeController());
        controllers.put("/user/login", new LoginController());
        controllers.put("/user/logout", new LogoutController());
        controllers.put("/user/list", new ListUserController());
        controllers.put("/user/updateForm", new UpdateUserFormController());
        controllers.put("/user/update", new UpdateUserController());
        controllers.put("/user/signup", new CreateUserController());
    }

    public static Controller getController(String controllerName) {
        return controllers.get(controllerName);
    }
}
