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
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmtSetter.setParameters(pstmt);
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
    }

    public List<User> query(String sql, RowMapper rowMapper) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<>();

        try {
            conn = ConnectionManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                User user = rowMapper.mapRow(rs);
                users.add(user);
            }
        }  finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
        return users;
    }

    public User queryForObject(String sql, PreparedStatementSetter pstmtSetter, RowMapper rowMapper) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;

        try {
            conn = ConnectionManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmtSetter.setParameters(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = rowMapper.mapRow(rs);
            }
        }  finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
        return user;
    }
}
