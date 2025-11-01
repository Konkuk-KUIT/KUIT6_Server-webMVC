package core.jdbc;

import java.util.List;

public class SelectJdbcTemplate<T> {
    private final JdbcTemplate<T> tmpl = new JdbcTemplate<>();

    public List<T> query(String sql, RowMapper<T> rowMapper) {
        return tmpl.query(sql, rowMapper);
    }

    public T queryForObject(String sql, PreparedStatementSetter pss, RowMapper<T> rowMapper) {
        return tmpl.queryForObject(sql, pss, rowMapper);
    }
}