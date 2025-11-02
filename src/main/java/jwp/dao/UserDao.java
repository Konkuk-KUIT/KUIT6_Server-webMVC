package jwp.dao;

import core.jdbc.*;
import jwp.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserDao {
    //Todo: insert, update, delete 기능
    private final JdbcTemplate<User> jdbcTemplate = new JdbcTemplate<>();

    public void insert(User user) throws SQLException {
        String sql = "INSERT INTO USERS VALUES(?, ?, ?, ?)";
        PreparedStatementSetter ps = pstmt -> {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
        };
        jdbcTemplate.update(sql, ps);

    }

    public void update(User user) throws SQLException {
        String sql = "UPDATE USERS SET password =?, name =?, email =? WHERE userId =?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getUserId());
        };
        jdbcTemplate.update(sql, pstmtSetter);
    }

    public void delete(User user) throws SQLException {
        String sql = "DELETE FROM USERS WHERE userId =?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, user.getUserId());
        };
        jdbcTemplate.update(sql, pstmtSetter);

    }

    //TODO: findAll, findByUserId
    public List<User> findAll() throws SQLException {
        String sql = "SELECT * FROM USERS";
        RowMapper rowMapper = rs ->
                new User(rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email"));
        return jdbcTemplate.query(sql, rowMapper);
    }

    public User findByUserId(String userId) throws SQLException {
        String sql = "SELECT * FROM USERS WHERE userId=?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, userId);
        };
        RowMapper rowMapper = rs ->
                new User(rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email"));
        return jdbcTemplate.queryForObject(sql, pstmtSetter, rowMapper);
    }
}
