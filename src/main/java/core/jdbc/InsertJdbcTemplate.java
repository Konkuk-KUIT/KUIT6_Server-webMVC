package core.jdbc;

public class InsertJdbcTemplate {
    private final JdbcTemplate<Void> tmpl = new JdbcTemplate<>();

    public void execute(String sql, PreparedStatementSetter pss) {
        tmpl.update(sql, pss);
    }
}