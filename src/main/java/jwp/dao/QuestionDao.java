package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Question;
import jwp.model.User;
import util.Keyholder;

import java.sql.SQLException;
import java.util.List;

public class QuestionDao {
    private final JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate();

    public List<Question> findAll() {
        String sql = "SELECT * FROM QUESTIONS";
        RowMapper rowMapper = rs -> new Question(
                rs.getLong("questionId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate"),
                rs.getInt("countOfAnswer"));

        return jdbcTemplate.query(sql, rowMapper);
    }

    public void insert(Question question) {
        String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) VALUES (?, ?, ?, ?, ?)";
        PreparedStatementSetter ps = pstmt -> {
            pstmt.setString(1, question.getWriter());
            pstmt.setString(2, question.getTitle());
            pstmt.setString(3, question.getContents());
            pstmt.setTimestamp(4, question.getCreatedDate());
            pstmt.setInt(5, question.getCountOfAnswer());
        };
        Keyholder holder = new Keyholder();
        jdbcTemplate.update2(sql, ps, holder);

        findByQuestionId(holder.getId());
    }

    public Question findByQuestionId(Long questionId) {
        String sql = "SELECT * FROM QUESTIONS WHERE questionId = ?";
        PreparedStatementSetter ps = pstmt -> {
            pstmt.setLong(1, questionId);
        };
        RowMapper<Question> rowMapper = rs -> new Question(
                rs.getLong("questionId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate"),
                rs.getInt("countOfAnswer")
        );
        return jdbcTemplate.queryForObject(sql, ps, rowMapper);
    }
}
