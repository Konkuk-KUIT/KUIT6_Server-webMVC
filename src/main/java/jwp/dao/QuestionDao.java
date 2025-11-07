package jwp.dao;
import jwp.enums.QuestionParameter;
import jwp.model.KeyHolder;
import jwp.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestionDao {
    private final EntityManager em;

    @Transactional
    public Question insert(Question question) throws SQLException {
       em.persist(question);
       return question;
    }

    // TODO findAll, findByUserId
    public List<Question> findAll() throws SQLException {
       return em.createQuery("SELECT q FROM Question q", Question.class).getResultList();
    }

    public Question findByQuestionId(Long questionId) throws SQLException {
       return em.find(Question.class, questionId);
    }
}
