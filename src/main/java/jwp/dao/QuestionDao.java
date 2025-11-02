package jwp.dao;

import core.jdbc.*;
import jwp.model.Question;
import java.sql.SQLException;
import java.util.List;

public class QuestionDao {
    private final SelectJdbcTemplate<Question> selectTpl = new SelectJdbcTemplate<>();
    private final InsertJdbcTemplate insertTpl = new InsertJdbcTemplate();
    private final QuestionRowMapper mapper = new QuestionRowMapper();

    public List<Question> findAll() {
        String sql = "SELECT * FROM QUESTIONS ORDER BY questionId DESC";
        return selectTpl.query(sql, mapper);
    }

    public Question findByQuestionId(long questionId) {
        String sql = "SELECT * FROM QUESTIONS WHERE questionId = ?";
        PreparedStatementSetter pss = pstmt -> pstmt.setLong(1, questionId);
        return selectTpl.queryForObject(sql, pss, mapper);
    }

    public Question insert(Question question) throws SQLException {
        String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new KeyHolder();

        PreparedStatementSetter pss = pstmt -> {
            pstmt.setString(1, question.getWriter());
            pstmt.setString(2, question.getTitle());
            pstmt.setString(3, question.getContents());
            pstmt.setTimestamp(4, java.sql.Timestamp.valueOf(question.getCreatedDate()));
            pstmt.setInt(5, question.getCountOfAnswer());
        };

        insertTpl.execute(sql, pss, keyHolder);
        return findByQuestionId(keyHolder.getId());
    }
}