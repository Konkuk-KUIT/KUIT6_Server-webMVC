package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.enums.UserParameter;
import jwp.model.KeyHolder;
import jwp.model.User;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.SQLException;
import java.util.List;

public class UserDao {
    // TODO insert, update, delete
    private final JdbcTemplate<User> jdbcTemplate = new JdbcTemplate();

    public UserDao() {}

    public static UserDao getInstance(){
        return new UserDao();
    }

    public void insert(User user) throws SQLException {
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        PreparedStatementSetter pss = pstmt -> {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
        };
        KeyHolder keyHolder = new KeyHolder();
        jdbcTemplate.update(sql, pss, keyHolder);
    }

    public void update(User user) throws SQLException {
        String sql = "UPDATE USERS SET password =?, name =?, email =? WHERE userId = ?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getUserId());
        };
        KeyHolder keyHolder = new KeyHolder();
        jdbcTemplate.update(sql, pstmtSetter, keyHolder);
    }

    public void delete(User user) throws SQLException {
        String sql = "DELETE  FROM USERS WHERE userId = ?";
        PreparedStatementSetter pss = pstmt -> {
            pstmt.setString(1, user.getUserId());
        };
        KeyHolder keyHolder = new KeyHolder();
        jdbcTemplate.update(sql, pss, keyHolder);
    }

    // TODO findAll, findByUserId
    public List<User> findAll() throws SQLException {
        String sql = "SELECT * FROM USERS";
        RowMapper<User> rowMapper = getUserRowMapper();
        return jdbcTemplate.query(sql, rowMapper);
    }

    public User findByUserId(String userId) throws SQLException {
        String sql = "SELECT * FROM USERS WHERE userId = ?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, userId);
        };
        RowMapper<User> rowMapper = getUserRowMapper();
        return jdbcTemplate.queryForObject(sql, pstmtSetter, rowMapper);
    }

    private static RowMapper<User> getUserRowMapper() {
        RowMapper<User> rowMapper = rs -> new User(rs.getString("userId"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email"));
        return rowMapper;
    }
}
