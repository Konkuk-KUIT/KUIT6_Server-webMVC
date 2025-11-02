package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Question;

import java.sql.SQLException;
import java.util.List;

public class QuestionDao {
    private final JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate<>();

    private final RowMapper<Question> rowMapper = rs -> new Question(
            rs.getLong("questionId"),
            rs.getString("writer"),
            rs.getString("title"),
            rs.getString("contents"),
            rs.getTimestamp("createdDate"),
            rs.getInt("countOfAnswer")
    );

    public Question insert(Question question) throws SQLException {
        String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) VALUES (?, ?, ?, NOW(), 0)";
        KeyHolder keyHolder = new KeyHolder();

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, question.getWriter());
            pstmt.setString(2, question.getTitle());
            pstmt.setString(3, question.getContents());
        };

        jdbcTemplate.update(sql, pstmtSetter, keyHolder);

        return findByQuestionId(keyHolder.getId());
    }

    public List<Question> findAll() throws SQLException {
        String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS";

        return jdbcTemplate.query(sql, rowMapper);
    }

    public Question findByQuestionId(long questionId) throws SQLException {
        String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS WHERE questionId = ?";

        return jdbcTemplate.queryForObject(
                sql,
                pstmt -> pstmt.setLong(1, questionId),
                rowMapper
        );
    }

}
