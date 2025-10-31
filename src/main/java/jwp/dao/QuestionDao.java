package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Question;
import jwp.model.User;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
                rs.getTimestamp("createdDate"),
                rs.getInt("countOfAnswer")
        );
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Question insert(Question question) throws SQLException {
        String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new KeyHolder();
        PreparedStatementSetter pss = pstmt -> {
            pstmt.setString(1, question.getWriter());
            pstmt.setString(2, question.getTitle());
            pstmt.setString(3, question.getContents());
            pstmt.setTimestamp(4, question.getCreatedDate());
            pstmt.setString(5, question.getWriter());
        };

        jdbcTemplate.update(sql, pss, keyHolder);
        return findByQuestionId(keyHolder.getId());
    }

    public Question findByQuestionId(long questionId) throws SQLException {
        String sql = "SELECT * FROM QUESTIONS WHERE questionId = ?";

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setLong(1, questionId);
        };
        RowMapper<Question> rowMapper = rs -> new Question(
                rs.getLong("questionId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate"),
                rs.getInt("countOfAnswer"));


        return jdbcTemplate.queryForObject(sql, pstmtSetter, rowMapper);
    }
}
