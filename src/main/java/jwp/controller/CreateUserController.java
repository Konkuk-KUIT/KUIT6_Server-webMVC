package jwp.controller;

import jwp.dao.UserDao;
import jwp.enums.HttpUrl;
import jwp.enums.UserParameter;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CreateUserController implements Controller {
    @Override
    public String service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        createUser(req);
        return HttpUrl.REDIRECT.getValue().concat(HttpUrl.LIST.getValue());
    }

    private static void createUser(HttpServletRequest req) throws SQLException {
        User user = new User(req.getParameter(UserParameter.USER_ID.getValue()),
                req.getParameter(UserParameter.PASSWORD.getValue()),
                req.getParameter(UserParameter.NAME.getValue()),
                req.getParameter(UserParameter.EMAIL.getValue()));
        UserDao.getInstance().insert(user);
    }
}
