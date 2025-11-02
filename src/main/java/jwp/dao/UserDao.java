package jwp.dao;

import core.jdbc.ConnectionManager;
import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.User;
import org.apache.jasper.compiler.JDTCompiler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private final JdbcTemplate<User> jdbcTemplate = new JdbcTemplate<>();

    public void insert(User user) throws SQLException {
        String sql = "insert into users values (?, ?, ?, ?)";
        PreparedStatementSetter setter = ps -> {
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getUserId());
        };
        jdbcTemplate.update(sql, setter);
    }

    public void update(User user) throws SQLException {
        String sql = "update users set password = ?, name = ?, email = ? where userId = ?";
        PreparedStatementSetter setter = ps -> {
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getUserId());
        };
        jdbcTemplate.update(sql, setter);
    }

    public void delete(User user) throws SQLException {
        String sql = "delete from users where userId = ?";
        PreparedStatementSetter setter = ps -> {
            ps.setString(1, user.getUserId());
        };
        jdbcTemplate.update(sql, setter);
    }

    public List<User> findAll() throws SQLException {
        String sql = "select * from users";
        RowMapper rowMapper = rs -> new User(rs.getString("userId"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email"));
        return jdbcTemplate.query(sql, rowMapper);
    }

    public User findByUserId(String userId) throws SQLException {
        String sql = "select * from users where userId = ?";
        PreparedStatementSetter setter = ps -> {
            ps.setString(1, userId);
        };
        RowMapper rowMapper = rs -> new User(rs.getString("userId"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email"));
        return jdbcTemplate.queryForObject(sql, setter, rowMapper);
    }
}
