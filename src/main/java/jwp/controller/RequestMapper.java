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
        controllerMap.put("/user/create", new CreateUserController());
        controllerMap.put("/qna/create", new CreateQuestionController());
        controllerMap.put("/qna/form", new CreateQuestionFormController());
        controllerMap.put("/qna/show", new ShowQuestionController());

    }

    public Controller findController(String url) {
        return controllerMap.get(url
        );
    }
}
