package jwp.dao;
import jwp.model.KeyHolder;
import jwp.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserDao {
    // TODO insert, update, delete
    private final EntityManager em;

    @Transactional
    public void insert(User user){
        em.persist(user);
    }
    @Transactional
    public void update(User user) {
        em.merge(user);
    }

    // TODO findAll, findByUserId
    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    public User findByUserId(String userId) throws SQLException {
       return em.find(User.class, userId);
    }
}
