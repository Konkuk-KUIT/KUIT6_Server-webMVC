package jwp.dao;

import core.jdbc.*;
import jwp.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserDao {
    //todo : insert, update, delete

    private final JdbcTemplate<User> jdbcTemplate = new JdbcTemplate<User>();

    private static UserDao userDao;

    private UserDao() {}

    public static UserDao getInstance() {
        if (userDao == null) {
            userDao = new UserDao();
        }
        return userDao;
    }

    public void insert(User user) {
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        PreparedStatementSetter pss = pstmt -> {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
        };
        jdbcTemplate.update(sql, pss);
    }

    public void update(User user) {
        String sql = "UPDATE USERS SET password = ?, name = ?, email = ? WHERE userId = ?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getUserId());
        };
        jdbcTemplate.update(sql, pstmtSetter);
    }

    public void delete(User user) {
        String sql = "DELETE FROM USERS WHERE userId = ?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, user.getUserId());
        };
        jdbcTemplate.update(sql, pstmtSetter);
    }

    //TODO fineAll, findByUserId
    public List<User> findAll() {

        String sql = "SELECT * FROM USERS";
        RowMapper<User> rowMapper = rs -> new User(rs.getString("userId"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email"));

        return jdbcTemplate.query(sql, rowMapper);

    }

    public User findByUserId(String userId) {
        String sql = "SELECT * FROM USERS  WHERE userId = ?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, userId);
        };

        RowMapper<User> rowMapper = rs -> new User(rs.getString("userId"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email"));
        return jdbcTemplate.queryForObject(sql, pstmtSetter, rowMapper);
    }
}
