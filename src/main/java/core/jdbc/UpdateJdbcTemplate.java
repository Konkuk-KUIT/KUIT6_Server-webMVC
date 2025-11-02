package core.jdbc;

import java.sql.SQLException;

public class UpdateJdbcTemplate {
    private final JdbcTemplate<Void> tmpl = new JdbcTemplate<>();

    public void execute(String sql, PreparedStatementSetter pss) {
        tmpl.update(sql, pss);
    }
}