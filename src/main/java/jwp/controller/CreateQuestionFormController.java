package jwp.controller;

import jwp.enums.HttpUrl;
import jwp.enums.JspUrl;
import jwp.util.UserSessionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class CreateQuestionFormController implements Controller {
    @Override
    public String service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        HttpSession session = req.getSession();
        if (UserSessionUtils.isLogined(session)) {
            return JspUrl.QNA_FORM.getValue();
        }
        return HttpUrl.REDIRECT.getValue().concat(HttpUrl.LOGIN.getValue());
    }
}
