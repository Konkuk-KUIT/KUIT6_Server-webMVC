package core.jdbc;

import jwp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
    public void update(String sql, PreparedStatementSetter pstmtSetter) throws SQLException {

        try (
                Connection conn = ConnectionManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmtSetter.setParameters(pstmt);
            pstmt.executeUpdate();
        }
    }

    public List<User> query(String sql, RowMapper rowMapper) throws SQLException {
        List<User> users = new ArrayList<>();

        try (
                Connection conn = ConnectionManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
        ) {
            while (rs.next()) {
                User user = rowMapper.mapRow(rs);
                users.add(user);
            }
        }
        return users;
    }

    public User queryForObject(String sql, PreparedStatementSetter pstmtSetter, RowMapper rowMapper) throws SQLException {
        User user = null;
        ResultSet rs = null;

        try (
                Connection conn = ConnectionManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            rs = pstmt.executeQuery();
            pstmtSetter.setParameters(pstmt);

            if (rs.next()) {
                user = rowMapper.mapRow(rs);
            }
        } finally {
            if (rs != null) {rs.close();}
        }
        return user;
    }
}
