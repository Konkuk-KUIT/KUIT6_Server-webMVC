package jwp.dao;

import jwp.model.Answer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

@Repository // 요구사항 2
@RequiredArgsConstructor // 요구사항 3
public class AnswerDao {

    private final EntityManager em;

    // findAllByQuestionId (JDBC -> JPQL)
    public List<Answer> findAllByQuestionId(long questionId) throws SQLException {
        return em.createQuery("select a from Answer a where a.question.questionId = :id", Answer.class)
                .setParameter("id", questionId)
                .getResultList();
    }

    // (이후 답변 기능 구현 시 필요)
    // @Transactional
    // public void insert(Answer answer) { ... }
}