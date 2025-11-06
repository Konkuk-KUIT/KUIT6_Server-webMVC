package jwp.dao;

import jwp.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public void insert(User user) {
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
            user.getUserId(),
            user.getPassword(),
            user.getName(),
            user.getEmail());
    }

    public void update(User user) {
        String sql = "UPDATE USERS SET password = ?, name = ?, email = ? WHERE userId = ?";
        jdbcTemplate.update(sql,
            user.getPassword(),
            user.getName(),
            user.getEmail(),
            user.getUserId());
    }

    public void delete(User user) {
        String sql = "DELETE FROM USERS WHERE userId = ?";
        jdbcTemplate.update(sql, user.getUserId());
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM USERS";
        RowMapper<User> rowMapper = (rs, rowNum) -> new User(
            rs.getString("userId"),
            rs.getString("password"),
            rs.getString("name"),
            rs.getString("email"));
        return jdbcTemplate.query(sql, rowMapper);
    }

    public User findByUserId(String userId) {
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userId=?";
        RowMapper<User> rowMapper = (rs, rowNum) -> new User(
            rs.getString("userId"),
            rs.getString("password"),
            rs.getString("name"),
            rs.getString("email"));
        return jdbcTemplate.queryForObject(sql, rowMapper, userId);
    }
}
