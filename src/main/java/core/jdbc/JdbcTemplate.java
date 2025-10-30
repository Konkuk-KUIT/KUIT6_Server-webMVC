package core.jdbc;
import core.db.Keyholder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate<T> {

    public void update(String sql, PreparedStatementSetter pstmtSetter){
        try(Connection conn = ConnectionManager.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmtSetter.setParameters(pstmt);
            pstmt.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException("데이터 변경 오류",e);
        }
    }

    public Long update(String sql, PreparedStatementSetter pstmtSetter, Keyholder keyholder){
        try (Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmtSetter.setParameters(pstmt);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                keyholder.setId(rs.getLong(1));
            }
            rs.close();
            return keyholder.getId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<T> query(String sql, RowMapper<T> rowMapper)  {
        List<T> Objects = new ArrayList<>();
        try(Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {
            while(rs.next()) {
                T Object = rowMapper.mapRow(rs);
                Objects.add(Object);
            }
        }catch (SQLException e) {
            throw new RuntimeException("데이터 변경 오류",e);
        }
        return Objects;
    }
    public T queryForObject(String sql, PreparedStatementSetter pstmtSetter, RowMapper<T> rowMapper) {
        ResultSet rs = null;
        T object = null;
        try(Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmtSetter.setParameters(pstmt);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                object = rowMapper.mapRow(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("데이터 변경 오류",e);
        }
        return object;
    }
}
