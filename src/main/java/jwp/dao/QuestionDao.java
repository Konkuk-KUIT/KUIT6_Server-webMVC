package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Question;

import java.sql.Timestamp;
import java.util.List;

public class QuestionDao {
    private final JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate<>();

    public void insert(Question question) {
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setLong(1, question.getQuestionId());
            pstmt.setString(2, question.getWriter());
            pstmt.setString(3, question.getTitle());
            pstmt.setObject(4, question.getCreatedDate());
            pstmt.setString(5, question.getContents());
            pstmt.setInt(6, question.getCountOfAnswer());
        };
        jdbcTemplate.update(sql, pstmtSetter);
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
