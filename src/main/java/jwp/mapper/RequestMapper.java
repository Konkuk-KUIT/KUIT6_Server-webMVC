package jwp.mapper;

import jwp.controller.*;

import java.util.HashMap;

public class RequestMapper {

    private static final RequestMapper instance = new RequestMapper();
    private final HashMap<String, Controller> map = new HashMap<String, Controller>();

    private RequestMapper() {
        init();
    }

    public static RequestMapper getInstance() {
        return instance;
    }

    private void init() {
        map.put("/user/signup", new CreateUserController());
        map.put("/", new HomeController());
        map.put("/user/list", new ListUserController());
        map.put("/user/login", new LoginController());
        map.put("/user/logout", new LogoutController());
        map.put("/user/update", new UpdateUserController());
        map.put("/user/updateForm", new UpdateUserFormController());
    }

    public Controller mappedController (String path) {
        return map.get(path);
    }
}
