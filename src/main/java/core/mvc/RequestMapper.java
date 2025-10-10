package core.mvc;

import jwp.controller.*;

import javax.servlet.ServletException;
import java.util.HashMap;
import java.util.Map;

public class RequestMapper {
    private static final Map<String, Controller> mappingController = new HashMap<>();
    public void init() throws ServletException {
        mappingController.put("/", new HomeController());
        mappingController.put("/user/signup", new CreateUserController());
        mappingController.put("/user/list", new ListUserController());
        mappingController.put("/user/login", new LoginController());
        mappingController.put("/user/logout", new LogoutController());
        mappingController.put("/user/updateForm", new UpdateUserFormController());
        mappingController.put("/user/update", new UpdateUserInfoController());
    }

    public Controller get(String path) {
        return mappingController.get(path);
    }
}
