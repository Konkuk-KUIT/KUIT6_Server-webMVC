package core.jdbc;

public class UpdateJdbcTemplate {
    private final jdbcTemplate<Void> tmpl = new jdbcTemplate<>();
    public void execute(String sql, PreparedStatementSetter pss) {
        tmpl.update(sql, pss);
    }
}
