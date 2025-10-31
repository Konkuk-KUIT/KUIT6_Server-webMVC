package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.enums.JspUrl;
import jwp.enums.ReqAttribute;
import jwp.model.Question;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class HomeController implements Controller {
    @Override
    public String service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        List<Question> qustions = QuestionDao.getInstance().findAll();
        req.setAttribute(ReqAttribute.QUESTIONS.getValue(), qustions);
        return JspUrl.HOME.getValue();
    }
}
