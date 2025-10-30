package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Answer;

import java.util.List;

public class AnswerDao {

    private final JdbcTemplate<Answer> jdbcTemplate = new JdbcTemplate<>();

    private static AnswerDao answerDao;

    private AnswerDao() {}

    public static AnswerDao getInstance() {
        if (answerDao == null) {
            answerDao = new AnswerDao();
        }
        return answerDao;
    }

    public List<Answer> findByQuestionId(long questionId) {
        String sql = "SELECT * FROM ANSWERS WHERE questionId = ?";
        PreparedStatementSetter pss = pstmt -> {
            pstmt.setLong(1, questionId);
        };

        RowMapper<Answer> rowMapper = rs -> new Answer(
                rs.getInt("answerId"),
                rs.getString("writer"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate"),
                rs.getLong("questionId")
        );

        return jdbcTemplate.queryForObjects(sql, pss, rowMapper);
    }

}
