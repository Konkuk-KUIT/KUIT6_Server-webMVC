package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.enums.HttpUrl;
import jwp.enums.QuestionParameter;
import jwp.model.Question;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CreateQustionController implements Controller {
    @Override
    public String service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        Question question = new Question(req.getParameter(QuestionParameter.WRITER.getValue()),
                req.getParameter(QuestionParameter.TITLE.getValue()),
                req.getParameter(QuestionParameter.CONTENTS.getValue())
             );
        QuestionDao.getInstance().insert(question);
        return HttpUrl.REDIRECT.getValue().concat(HttpUrl.ROOT.getValue());
    }
}
