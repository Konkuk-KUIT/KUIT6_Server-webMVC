package jwp.dao;

import jwp.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@RequiredArgsConstructor // final 필드를 가진 생성자를 자동으로 만들어줌 -> DI
public class UserDao {

    private final EntityManager em;

    @Transactional // DB 변경은 꼭 트랜잭션 안에서...
    public void insert(User user) {
        em.persist(user); // SQL문을 직접 작성하지 않음. JPA가 객체와 DB를 자동으로 매핑
    }

    public void update(User user) {
        em.merge(user); // update 할 때. user 객체 안의 값들을 알아서 보고 DB에 반영
    }

    public List<User> findAll() {
                // 결과를 User.class 타입으로 매핑
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    public User findByUserId(String userId) {
        return em.find(User.class, userId); // PK로 엔티티 조회할 때 사용
    }
}
