package jwp.dao;

import core.db.Keyholder;

import jwp.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestionDao {
    private final EntityManager em;
    private final Keyholder keyHolder = new Keyholder();

    @Transactional
    public void insert(Question question){
        em.persist(question);
    }

    public List<Question> findAll(){
        return em.createQuery("select q from Question q", Question.class).getResultList();
    }

    public Question findById(Long questionId) {
        return em.find(Question.class, questionId);
    };

}
