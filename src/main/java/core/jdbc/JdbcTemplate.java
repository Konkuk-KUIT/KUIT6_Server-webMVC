package core.jdbc;

import jwp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate<T> {

    public void update(String sql, PreparedStatementSetter setter) throws SQLException {
        try(Connection con = ConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);) {
            setter.setParameters(ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<T> query(String sql, RowMapper<T> rowMapper) throws SQLException {
        List<T> users = new ArrayList<T>();
        try(Connection con = ConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                T user = rowMapper.mapRow(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public T queryForObject(String sql, PreparedStatementSetter setter, RowMapper<T> rowMapper) throws SQLException {
        T user = null;
        try(Connection con = ConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            setter.setParameters(ps);
            if (rs.next()) {
                user = rowMapper.mapRow(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }
}
