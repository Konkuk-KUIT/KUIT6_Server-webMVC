package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.KeyHolder;
import jwp.model.Question;

import java.util.List;

public class QuestionDao {

    JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate<>();

    private static QuestionDao questionDao;

    private QuestionDao() {}

    public static QuestionDao getInstance() {
        if(questionDao == null) {
            questionDao = new QuestionDao();
        }
        return questionDao;
    }

    //todo : insert 메서드 추가하기
    //todo : KeyHolder?를 통해 Question 객체의 ID값을 받아온 후 findById를 호출해 반환하도록 함
    public Question insert(Question question) {

        String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) VALUES(?, ?, ?, ?, ?)";
        PreparedStatementSetter pss = pstmt -> {
            pstmt.setString(1, question.getWriter());
            pstmt.setString(2, question.getTitle());
            pstmt.setString(3, question.getContents());
            pstmt.setTimestamp(4, question.getCreatedDate());
            pstmt.setInt(5, question.getCountOfAnswer());
        };

        KeyHolder keyHolder = new KeyHolder();

        jdbcTemplate.update(sql, pss, keyHolder);

        return findByQuestionId(keyHolder.getId());
    }


    //todo : Question 테이블의 모든 값 반환하는 메서드
    public List<Question> findAll() {

        String sql = "SELECT * FROM QUESTIONS";
        RowMapper<Question> rowMapper = rs -> new Question(
                rs.getInt("questionId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate"),
                rs.getInt("countOfAnswer")
        );

        return jdbcTemplate.query(sql, rowMapper);
    }

    public Question findByQuestionId(long id) {

        String sql = "SELECT * FROM QUESTIONS WHERE questionId = ?";
        PreparedStatementSetter pss = pstmt -> {
            pstmt.setLong(1, id);
        };

        RowMapper<Question> rowMapper = rs -> new Question(
                rs.getLong("questionId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate"),
                rs.getInt("countOfAnswer")
        );

        return jdbcTemplate.queryForObject(sql, pss, rowMapper);
    }
}
