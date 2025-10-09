package jwp.controller.mapping;

import jwp.controller.*;

import java.util.HashMap;
import java.util.Map;

public class RequestMapper implements ControllerMapping {
    private static final RequestMapper instance = new RequestMapper();
    private final Map<String, Controller> mappings = new HashMap<>();

    private RequestMapper() {
        mappings.put("/", new HomeController());
        mappings.put("/user/login", new UserLoginController());
        mappings.put("/user/logout", new UserLogoutController());
        mappings.put("/user/signup", new CreateUserController());
        mappings.put("/user/list", new ListUserController());
        mappings.put("/user/updateForm", new UpdateUserFormController());
        mappings.put("/user/update", new UpdateUserController());
    }

    public static RequestMapper getInstance() {
        return instance;
    }

    @Override
    public Controller mappingController(String uri) {
        return mappings.get(uri);
    }
}
