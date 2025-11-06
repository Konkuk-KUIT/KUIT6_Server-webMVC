package jwp.dao;

import jwp.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestionDao {

    private final JdbcTemplate jdbcTemplate;

    public Question insert(Question question) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"questionId"});
            ps.setString(1, question.getWriter());
            ps.setString(2, question.getTitle());
            ps.setString(3, question.getContents());
            ps.setObject(4, question.getCreatedDate());
            return ps;
        }, keyHolder);

        return findByQuestionId(keyHolder.getKey().intValue());
    }

    public void update(Question question) {
        String sql = "UPDATE QUESTIONS SET title = ?, contents = ?, createdDate = ? WHERE questionId = ?";
        jdbcTemplate.update(sql,
            question.getTitle(),
            question.getContents(),
            question.getCreatedDate(),
            question.getQuestionId());
    }

    public void delete(int questionId) {
        String sql = "DELETE FROM QUESTIONS WHERE questionId = ?";
        jdbcTemplate.update(sql, questionId);
    }

    public List<Question> findAll() {
        String sql = "SELECT * FROM QUESTIONS ORDER BY questionId";
        RowMapper<Question> rowMapper = (rs, rowNum) -> new Question(
            rs.getInt("questionId"),
            rs.getString("writer"),
            rs.getString("title"),
            rs.getString("contents"),
            rs.getDate("createdDate"),
            rs.getInt("countOfAnswer"));
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Question findByQuestionId(int questionId) {
        String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer " +
                "FROM QUESTIONS WHERE questionId=?";

        RowMapper<Question> rowMapper = (rs, rowNum) -> new Question(
            rs.getInt("questionId"),
            rs.getString("writer"),
            rs.getString("title"),
            rs.getString("contents"),
            rs.getDate("createdDate"),
            rs.getInt("countOfAnswer"));

        return jdbcTemplate.queryForObject(sql, rowMapper, questionId);
    }
}
