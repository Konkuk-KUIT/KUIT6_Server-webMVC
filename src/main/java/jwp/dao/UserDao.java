package jwp.dao;

import core.jdbc.InsertJdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import core.jdbc.SelectJdbcTemplate;
import core.jdbc.UpdateJdbcTemplate;
import jwp.model.User;

import java.util.List;

public class UserDao {
    private final InsertJdbcTemplate insertTpl = new InsertJdbcTemplate();
    private final UpdateJdbcTemplate updateTpl = new UpdateJdbcTemplate();
    private final SelectJdbcTemplate<User> selectTpl = new SelectJdbcTemplate<>();

    public void insert(User user) {
        String sql = "INSERT INTO USERS VALUES (?,?,?,?)";
        insertTpl.execute(sql, ps -> {
            ps.setString(1, user.getUserId());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setString(4, user.getEmail());
        });

    }

    public void update(User user)  {
        String sql = "UPDATE USERS SET password =?, name =?, email =? WHERE userId =?";
        updateTpl.execute(sql, ps -> {
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getUserId());
        });

    }

    public void delete(User user)  {
        String sql = "DELETE FROM USERS WHERE userId =?";
        updateTpl.execute(sql, ps -> ps.setString(1, user.getUserId()));

    }

    public List<User> findAll()  {
        String sql = "SELECT * FROM USERS";
        RowMapper<User> mapper = rs -> new User(
                rs.getString("userId"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email"));
        return selectTpl.query(sql, mapper);

    }

    public User findByUserId(String userId) {
        String sql = "SELECT * FROM USERS WHERE userId = ?";
        PreparedStatementSetter pss = ps -> ps.setString(1, userId);
        RowMapper<User> mapper = rs -> new User(
                rs.getString("userId"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email"));
        return selectTpl.queryForObject(sql, pss, mapper);

}

}
