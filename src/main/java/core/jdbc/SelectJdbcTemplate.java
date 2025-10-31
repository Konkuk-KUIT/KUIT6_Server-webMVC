package core.jdbc;

import java.sql.SQLException;
import java.util.List;

public class SelectJdbcTemplate<T> {
    private final jdbcTemplate<T> template = new jdbcTemplate<>();

    public List<T> query(String sql, RowMapper<T> rowMapper) {
        return template.query(sql, rowMapper);
    }

    public List<T> query(String sql, PreparedStatementSetter pss, RowMapper<T> rowMapper)  {
        return template.query(sql, pss, rowMapper);
    }

    public T queryForObject(String sql, PreparedStatementSetter pss, RowMapper<T> rowMapper)  {
        return template.queryForObject(sql, pss, rowMapper);
    }
}
