package jwp.dao;

import core.jdbc.QuestionRowMapper;
import core.jdbc.SelectJdbcTemplate;
import jwp.model.Question;

import java.util.List;

public class QuestionDao {
    private final SelectJdbcTemplate<Question> selectTpl = new SelectJdbcTemplate<>();
    private final QuestionRowMapper mapper = new QuestionRowMapper();

    public List<Question> findAll() {
        String sql = "SELECT * FROM QUESTIONS ORDER BY questionId DESC";
        return selectTpl.query(sql, mapper);
    }


}
