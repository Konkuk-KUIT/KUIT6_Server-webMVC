package core.mvc;

import jwp.controller.*;

import java.util.HashMap;
import java.util.Map;

public class RequestMapper {
    private static Map<String, Controller> controllers =  new HashMap<>();

    public RequestMapper() {}

    static {
        controllers.put("/", new HomeController());
        controllers.put("/user/signup", new CreateUserController());
        controllers.put("/user/login", new LoginController());
        controllers.put("/user/logout", new LogoutController());
        controllers.put("/user/list", new ListUserController());
        controllers.put("/user/updateForm", new UpdateUserFormController());
        controllers.put("/user/update", new UpdateUserController());
        controllers.put("/user/loginForm", new ForwardingController("/user/login.jsp"));
        controllers.put("/user/signupForm", new ForwardingController("/user/form.jsp"));
        controllers.put("/user/loginFailed", new ForwardingController("/user/loginFailed.jsp"));

        controllers.put("/qna/form", new CreateQuestionFormController());
        controllers.put("/qna/create", new CreateQuestionController());

        controllers.put("/qna/show", new ShowQuestionController());
    }

    public Controller getController(String url) {
        return controllers.get(url);
    }
}
