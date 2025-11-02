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

    public List<Question> findAll() throws SQLException {
        String sql = "SELECT * FROM QUESTIONS";

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
    // RowMapper를 공통으로 사용하기 위해 멤버 변수로 추출
    private final RowMapper<Question> rowMapper = rs -> new Question(
            rs.getLong("questionId"),
            rs.getString("writer"),
            rs.getString("title"),
            rs.getString("contents"),
            rs.getTimestamp("createdDate"),
            rs.getInt("countOfAnswer")
    );

    public Question findByQuestionId(long questionId) throws SQLException {
        String sql = "SELECT * FROM QUESTIONS WHERE questionId = ?";

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setLong(1, questionId);
        };

        return jdbcTemplate.queryForObject(sql, pstmtSetter, rowMapper);
    }

    public Question insert(String writer, String title, String contents) throws SQLException {
        String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) VALUES (?, ?, ?, CURRENT_TIMESTAMP(), 0)";
        KeyHolder keyHolder = new KeyHolder();

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, writer);
            pstmt.setString(2, title);
            pstmt.setString(3, contents);
        };

        jdbcTemplate.update(sql, pstmtSetter, keyHolder);

        // KeyHolder로 받은 ID를 이용해 findByQuestionId 호출하여 Question 객체 반환
        return findByQuestionId(keyHolder.getId());
    }
}