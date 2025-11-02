package core.jdbc;

import java.sql.*;

public class InsertJdbcTemplate {
    public void execute(String sql, PreparedStatementSetter pss, KeyHolder keyHolder) {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pss.setParameter(pstmt);
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    keyHolder.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }
}