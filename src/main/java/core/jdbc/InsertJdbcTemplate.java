package core.jdbc;


public class InsertJdbcTemplate {
    private final jdbcTemplate<Void> tmpl = new jdbcTemplate<>();
    public void execute(String sql, PreparedStatementSetter pss) {
        tmpl.update(sql, pss);
    }

    public void execute(String sql, PreparedStatementSetter pss, KeyHolder holder) {
        tmpl.update(sql, pss, holder);
    }
}
