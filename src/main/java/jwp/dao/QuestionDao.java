package jwp.dao;

import jwp.model.Question;
import jwp.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import util.Keyholder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestionDao {
    private final EntityManager em;

    public List<Question> findAll() {
        return em.createQuery("select q from Question q", Question.class).getResultList();
    }

    @Transactional
    public void insert(Question question) {
        em.persist(question);
    }

    public Question findByQuestionId(Long questionId) {
        return em.find(Question.class, questionId);
    }
}
