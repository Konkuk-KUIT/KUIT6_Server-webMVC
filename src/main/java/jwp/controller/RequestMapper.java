package jwp.controller;

import jwp.enums.HttpUrl;

import java.util.HashMap;
import java.util.Map;

public class RequestMapper {
    Map<String,Controller> controllers = new HashMap<>();

    private RequestMapper() {
        putControllerAll();
    }

    public static RequestMapper getInstance() {
        return new RequestMapper();
    }

    private void putControllerAll() {
        controllers.put(HttpUrl.ROOT.getValue(), new HomeController());
        controllers.put(HttpUrl.SIGNUP.getValue(), new CreateUserController());
        controllers.put(HttpUrl.LOGIN.getValue(), new LoginUserController());
        controllers.put(HttpUrl.LOGOUT.getValue(), new LogoutUserController());
        controllers.put(HttpUrl.LIST.getValue(), new ListUserController());
        controllers.put(HttpUrl.UPDATE_FORM.getValue(), new UpdateFormController());
        controllers.put(HttpUrl.UPDATE.getValue(), new UpdateUserController());
        controllers.put(HttpUrl.QNA_FORM.getValue(), new CreateQuestionFormController());
        controllers.put(HttpUrl.QNA_CREATE.getValue(), new CreateQustionController());
        controllers.put(HttpUrl.QNA_SHOW.getValue(), new ShowController());
    }

    public Controller getValue(String path) {
        return controllers.get(path);
    }
}
