package jwp.dao;

import jwp.model.Question;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Getter
@Setter
public class QuestionDao {

    private final EntityManager em;

    @Transactional
    public Question insert(Question question) {
        em.persist(question);
        return question;
    }

    @Transactional
    public void update(Question question) {
        em.merge(question);
    }

    public List<Question> findAll() {
        return em.createQuery("select q from Question q", Question.class).getResultList();
    }

    public Question findByQuestionId(int questionId) {
        return em.find(Question.class, questionId);
    }
}
