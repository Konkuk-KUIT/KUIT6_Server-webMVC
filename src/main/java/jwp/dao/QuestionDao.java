package jwp.dao;

import jwp.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

@Repository // 요구사항 2: 데이터 접근 계층 Bean으로 등록
@RequiredArgsConstructor // 요구사항 3: 생성자 자동 주입
public class QuestionDao {

    private final EntityManager em; // 생성자 주입

    // findAll (JDBC -> JPQL)
    public List<Question> findAll() throws SQLException {
        return em.createQuery("select q from Question q", Question.class)
                .getResultList();
    }

    // findByQuestionId (JDBC -> JPA find)
    public Question findByQuestionId(long questionId) throws SQLException {
        return em.find(Question.class, questionId);
    }

    // insert (JDBC -> JPA persist)
    @Transactional // 데이터를 변경하므로 트랜잭션 필요
    public Question insert(Question question) throws SQLException {
        em.persist(question);
        return question; // persist 후 영속화된 객체 반환
    }
}