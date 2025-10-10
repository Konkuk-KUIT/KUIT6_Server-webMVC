package jwp.controller;

import java.util.HashMap;
import java.util.Map;

public class RequestMapper {
    private static final Map<String, Controller> controllers = new HashMap<>();
    static {
        controllers.put("/", new HomeController());
        controllers.put("/user/list", new ListUserController());
        controllers.put("/user/login",new LoginController());
        controllers.put("/user/logout",new LogOutController());
        controllers.put("/user/update",new UpdateUserController());
        controllers.put("/user/updateForm",new UpdateUserFormController());
        controllers.put("/user/signup", new CreateUserController());

    }

    public static Controller getController(String path) {
        return controllers.get(path);
    }
}
