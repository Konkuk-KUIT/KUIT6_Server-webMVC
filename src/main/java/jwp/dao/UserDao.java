package jwp.dao;

import jwp.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserDao {

    private final EntityManager em;

    @Transactional
    public void insert(User user) throws SQLException {
        em.persist(user);
    }

    @Transactional
    public void update(User user) throws SQLException {
        em.merge(user);
    }

    public List<User> findAll() throws SQLException {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    public User findByUserId(String userId) throws SQLException {
        return em.find(User.class, userId);
    }
}
