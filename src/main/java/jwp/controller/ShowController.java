package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.enums.JspUrl;
import jwp.enums.QuestionParameter;
import jwp.enums.ReqAttribute;
import jwp.model.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ShowController implements Controller {
    @Override
    public String service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        Question question = QuestionDao.getInstance().findByQuestionId(req.getParameter(QuestionParameter.QUESTION_ID.getValue()));
        req.setAttribute(ReqAttribute.QUESTION.getValue(), question);
        return JspUrl.QNA_SHOW.getValue();
    }
}
