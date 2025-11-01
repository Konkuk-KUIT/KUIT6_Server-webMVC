package core.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class jdbcTemplate<T> {
    public void update(String sql, PreparedStatementSetter pstmtSetter) {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql) ){
            pstmtSetter.setParameters(pstmt);
            pstmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(String sql, PreparedStatementSetter pstmtSetter, KeyHolder holder) {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            pstmtSetter.setParameters(pstmt);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                holder.setId((int) rs.getLong(1));
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<T> query(String sql, RowMapper<T> rowMapper) {

        List<T> objects = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    objects.add(rowMapper.mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return objects;
    }

    public List<T> query(String sql, PreparedStatementSetter pss, RowMapper<T> rowMapper) {

        List<T> objects = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pss.setParameters(pstmt);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    objects.add(rowMapper.mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return objects;
    }

    public T queryForObject(String sql, PreparedStatementSetter pss, RowMapper<T> rowMapper) {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pss.setParameters(pstmt);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (!rs.next()) return null;
                T obj = rowMapper.mapRow(rs);
                if (rs.next()) throw new SQLException("Expected single row, found multiple");
                return obj;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
