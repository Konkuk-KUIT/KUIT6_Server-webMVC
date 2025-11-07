package jwp.dao;

import jwp.model.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Setter
@Getter
public class UserDao {

    private final EntityManager em;

    @Transactional
    public void insert(User user) {
        em.persist(user);
    }

    @Transactional
    public void update(User user) {
        em.merge(user);
    }

    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    public User findByUserId(String userId) throws SQLException {
        return em.find(User.class, userId);
    }
}
