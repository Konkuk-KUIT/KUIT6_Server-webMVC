package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import jwp.model.Question;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDao {
    private final JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate<>();

    public List<Question> getAllQuestions() throws SQLException {
        String sql = "Select * FROM QUESTIONS";
        RowMapper<Question> rowMapper = rs -> new Question(
                rs.getLong("questionId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate").toLocalDateTime(),
                rs.getInt("countOfAnswer")
        );
        return jdbcTemplate.query(sql, rowMapper);
    }
}
