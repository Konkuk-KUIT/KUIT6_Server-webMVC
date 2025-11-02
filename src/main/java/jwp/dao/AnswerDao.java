package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Answer;

import java.sql.SQLException;
import java.util.List;

public class AnswerDao {
    private final JdbcTemplate<Answer> jdbcTemplate = new JdbcTemplate<>();

    private final RowMapper<Answer> rowMapper = rs -> new Answer(
            rs.getLong("answerId"),
            rs.getString("writer"),
            rs.getString("contents"),
            rs.getTimestamp("createdDate"),
            rs.getLong("questionId")
    );

    public List<Answer> findAllByQuestionId(long questionId) throws SQLException {
        String sql = "SELECT * FROM ANSWERS WHERE questionId = ?";

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setLong(1, questionId);
        };

        // 2단계에서 추가한 파라미터가 있는 query 메서드 사용
        return jdbcTemplate.query(sql, pstmtSetter, rowMapper);
    }
}