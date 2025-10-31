package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Question;

import java.sql.Timestamp;
import java.util.List;

public class QuestionDao {
    private final JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate<>();

    public Question insert(String writer, String title, String contents) {
        String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) VALUES (?, ?, ?, CURRENT_TIMESTAMP(), 0)";
        KeyHolder keyHolder = new KeyHolder();
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, writer);
            pstmt.setString(2, title);
            pstmt.setObject(3, contents);
        };
        jdbcTemplate.update(sql, pstmtSetter, keyHolder);
        return findByQuestionId((long) keyHolder.getId());
    }

    public void update(Question question) {
        String sql = "UPDATE USERS SET writer =?, title =?, createDate =?, contents =?, countOfAnswer =? WHERE questionId =?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, question.getWriter());
            pstmt.setString(2, question.getTitle());
            pstmt.setTimestamp(3, Timestamp.valueOf(question.getCreatedDate()));
            pstmt.setString(4, question.getContents());
            pstmt.setInt(5, question.getCountOfAnswer());
            pstmt.setLong(6, question.getQuestionId());

        };
        jdbcTemplate.update(sql, pstmtSetter);
    }

    public void delete(Question question) {
        String sql = "DELETE FROM QUESTIONS WHERE questionId =?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setLong(1, question.getQuestionId());
        };
        jdbcTemplate.update(sql, pstmtSetter);
    }

    public List<Question> findAll() {
        String sql = "SELECT * FROM QUESTIONS";
        RowMapper rowMapper = rs -> new Question(rs.getLong("questionId"),
                rs.getString("writer"),
                rs.getString("title"), rs.getTimestamp("createdDate").toLocalDateTime(),
                rs.getString("contents"), rs.getInt("countOfAnswer"));
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Question findByQuestionId(Long questionId) {
        String sql = "SELECT * FROM QUESTIONS WHERE questionId =?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setLong(1, questionId);
        };
        RowMapper rowMapper = rs -> new Question(rs.getLong("questionId"),
                rs.getString("writer"),
                rs.getString("title"), rs.getTimestamp("createdDate").toLocalDateTime(),
                rs.getString("contents"), rs.getInt("countOfAnswer"));
        return jdbcTemplate.queryForObject(sql, pstmtSetter, rowMapper);
    }
}
