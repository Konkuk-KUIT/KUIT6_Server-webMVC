package core.jdbc;

import jwp.model.User;
import util.Keyholder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate<T> {
    public void update(String sql ,PreparedStatementSetter pstmtSetter)  {

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);){

            pstmtSetter.setParameters(pstmt);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update2(String sql , PreparedStatementSetter pstmtSetter, Keyholder holder)  {
        try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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

    public List<T> query(String sql, RowMapper<T> rowMapper){
        List<T> objects = new ArrayList<>();

        try(Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();) {
            while(rs.next()){
                T object = rowMapper.mapRow(rs);
                objects.add(object);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return objects;
    }

    public T queryForObject(String sql, PreparedStatementSetter pstmtSetter, RowMapper<T> rowMapper){
        ResultSet rs = null;
        T object = null;

        //확실히 파라미터 바인딩이 된 이후에 rs가 resultSet이 실행이 되도록 설정을 해줘야함
        try(Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmtSetter.setParameters(pstmt);
            rs = pstmt.executeQuery();

            if(rs.next()){
                object = rowMapper.mapRow(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        }
        return object;
    }

}