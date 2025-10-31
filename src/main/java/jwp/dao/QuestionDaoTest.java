package jwp.dao;

import jwp.model.Question;
import java.util.List;

public class QuestionDaoTest {
    public static void main(String[] args) {
        QuestionDao dao = new QuestionDao();

        List<Question> questions = dao.findAll();
        for (Question q : questions) {
            System.out.println(q);
        }
    }
}