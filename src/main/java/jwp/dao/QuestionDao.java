package jwp.dao;

import core.db.Keyholder;
import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Question;


import java.util.List;

public class QuestionDao {
    private final JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate();

    public Long insert(Question question) {
        String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) VALUES(?, ?, ?, ?, ?)";        PreparedStatementSetter pstmtSetter = pstmt ->{
            pstmt.setString(1,question.getWriter());
            pstmt.setString(2,question.getTitle());
            pstmt.setString(3,question.getContents());
            pstmt.setTimestamp(4,question.getCreatedDateAsTimestamp());
            pstmt.setInt(5,question.getCountOfAnswer());

        };
        Keyholder keyHolder = new Keyholder();
        Long questionId = jdbcTemplate.update(sql, pstmtSetter, keyHolder);

        return questionId;
    }

    public List<Question> findAll(){
        String sql = "SELECT * FROM QUESTIONS";
        RowMapper<Question> rowMapper = rs -> new Question(
                rs.getLong("questionId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate").toLocalDateTime(),
                rs.getInt("countOfAnswer")
        );
        return jdbcTemplate.query(sql, rowMapper);
    }
    public Question findById(Long questionId) {
        String sql = "SELECT * FROM QUESTIONS WHERE questionId = ?";
        PreparedStatementSetter ps = pstmt -> {
            pstmt.setLong(1, questionId);
        };
        RowMapper<Question> rowMapper = rs -> new Question(
                rs.getLong("questionId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate").toLocalDateTime(),
                rs.getInt("countOfAnswer")
        );
        return jdbcTemplate.queryForObject(sql, ps, rowMapper);
    };

}
