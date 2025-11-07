package jwp.dao;

import jwp.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestionDao {
    private final EntityManager em;

    public void insert(Question question) {
        em.persist(question);
    }

    public List<Question> findAll() throws SQLException {
        return em.createQuery("select q from Question q", Question.class).getResultList();
    }

    public Question findByQuestionId(long questionId) throws SQLException {
        return em.find(Question.class, questionId);
    }

}
