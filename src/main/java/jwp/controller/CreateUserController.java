package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.enums.HttpUrl;
import jwp.enums.UserParameter;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateUserController implements Controller {
    @Override
    public String service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        createUser(req);
        return HttpUrl.REDIRECT.getValue().concat(HttpUrl.LIST.getValue());
    }

    private static void createUser(HttpServletRequest req) {
        User user = new User(req.getParameter(UserParameter.USER_ID.getValue()),
                req.getParameter(UserParameter.PASSWORD.getValue()),
                req.getParameter(UserParameter.NAME.getValue()),
                req.getParameter(UserParameter.EMAIL.getValue()));
        MemoryUserRepository.getInstance().addUser(user);
    }
}
