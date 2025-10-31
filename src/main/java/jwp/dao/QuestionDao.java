package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.enums.QuestionParameter;
import jwp.model.KeyHolder;
import jwp.model.Question;
import jwp.model.User;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class QuestionDao {
    private final JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate();

    public QuestionDao() {}

    public static QuestionDao getInstance(){
        return new QuestionDao();
    }

    public Question insert(Question question) throws SQLException {
        String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) VALUES (?, ?, ?, ?, ?)";
        PreparedStatementSetter pss = pstmt -> {
            pstmt.setString(1, question.getWriter());
            pstmt.setString(2, question.getTitle());
            pstmt.setString(3, question.getContent());
            pstmt.setTimestamp(4, question.getCreatedDate());
            pstmt.setInt(5, question.getCountOfAnswer());
        };
        KeyHolder keyHolder = new KeyHolder();
        jdbcTemplate.update(sql, pss, keyHolder);
        return findByQuestionId(String.valueOf(keyHolder.getId()));
    }

    // TODO findAll, findByUserId
    public List<Question> findAll() throws SQLException {
        String sql = "SELECT * FROM QUESTIONS";
        RowMapper<Question> rowMapper = getQuestionRowMapper();
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Question findByQuestionId(String questionId) throws SQLException {
        String sql = "SELECT * FROM QUESTIONS WHERE questionId = ?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, questionId);
        };
        RowMapper<Question> rowMapper = getQuestionRowMapper();
        return jdbcTemplate.queryForObject(sql, pstmtSetter, rowMapper);
    }

    private static RowMapper<Question> getQuestionRowMapper() {
        RowMapper<Question> rowMapper = rs -> new Question(rs.getLong(QuestionParameter.QUESTION_ID.getValue()),
                rs.getString(QuestionParameter.WRITER.getValue()),
                rs.getString(QuestionParameter.TITLE.getValue()),
                rs.getString(QuestionParameter.CONTENTS.getValue()),
                rs.getTimestamp(QuestionParameter.CREATED_DATE.getValue()),
                rs.getInt(QuestionParameter.COUNT_OF_ANSWER.getValue()));
        return rowMapper;
    }
}
