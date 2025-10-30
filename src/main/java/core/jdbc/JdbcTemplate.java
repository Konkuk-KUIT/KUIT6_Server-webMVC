package core.jdbc;



import jwp.model.KeyHolder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate<T> {

    public void update(String sql, PreparedStatementSetter pstmtSetter) {

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmtSetter.setParameters(pstmt);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Database update failed", e);
        }
    }

    public void update(String sql, PreparedStatementSetter pstmtSetter, KeyHolder holder) {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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

        try (
                Connection conn = ConnectionManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
        ) {
            while (rs.next()) {
                T object = rowMapper.mapRow(rs);
                objects.add(object);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Database query failed", e);
        }
        return objects;
    }

    public T queryForObject(String sql, PreparedStatementSetter pstmtSetter, RowMapper<T> rowMapper) {

        T object = null;

        try  (
                Connection conn = ConnectionManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmtSetter.setParameters(pstmt);
            try (ResultSet rs = pstmt.executeQuery()){

                if (rs.next()) {
                    object = rowMapper.mapRow(rs);
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Database queryForObject failed", e);
        }

        return object;
    }

    public List<T> queryForObjects(String sql, PreparedStatementSetter pstmtSetter, RowMapper<T> rowMapper) {

        List<T> objects = new ArrayList<>();

        try  (
                Connection conn = ConnectionManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmtSetter.setParameters(pstmt);
                try (ResultSet rs = pstmt.executeQuery()){
                    while (rs.next()) {
                        T object = rowMapper.mapRow(rs);
                        objects.add(object);
                    }
                }
            } catch (SQLException e) {
            throw new DataAccessException("Database queryForObject failed", e);
        }
        return objects;
    }
}

