package core.mvc;

import jwp.controller.*;

import java.util.HashMap;
import java.util.Map;

public class RequestMapper {
    private Map<String, Controller> controllers = new HashMap<>();

    // 애플리케이션의 모든 Controller를 등록
    public void init() {
        // 기존 Controller들을 새로 만든 프레임워크 규격에 맞게 등록합니다.
        controllers.put("/", new HomeController());
        controllers.put("/user/list", new ListUserController());
        controllers.put("/user/login", new LoginUserController());
        controllers.put("/user/logout", new LogoutUserController());
        controllers.put("/user/signup", new CreateUserController());
        controllers.put("/user/updateForm", new UpdateUserFormController());
        controllers.put("/user/update", new UpdateUserController());
        // 여기에 새로운 Controller가 생길 때마다 추가해주면 됩니다.
    }

    public Controller findController(String url) {
        return controllers.get(url);
    }
}