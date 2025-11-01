package jwp.dao;

import core.jdbc.*;
import jwp.model.Question;

import java.util.List;

public class QuestionDao {
    private final SelectJdbcTemplate<Question> selectTpl = new SelectJdbcTemplate<>();
    private final InsertJdbcTemplate insertTpl = new InsertJdbcTemplate();
    private final QuestionRowMapper mapper = new QuestionRowMapper();


    public Question findById(long id) {
        String sql = "SELECT * FROM QUESTIONS WHERE questionId = ?";
        return selectTpl.queryForObject(sql, ps -> ps.setLong(1, id), mapper);
    }

    public List<Question> findAll() {
        String sql = "SELECT * FROM QUESTIONS ORDER BY questionId DESC";
        return selectTpl.query(sql, mapper);
    }

    public Question insert(String writer, String title, String contents) {
        String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) " +
                "VALUES (?, ?, ?, CURRENT_TIMESTAMP(), 0)";

        KeyHolder kh = new KeyHolder();
        PreparedStatementSetter pss = ps -> {
            ps.setString(1, writer);
            ps.setString(2, title);
            ps.setString(3, contents);
        };

        insertTpl.execute(sql, pss, kh);
        return findById(kh.getId());
    }


}
