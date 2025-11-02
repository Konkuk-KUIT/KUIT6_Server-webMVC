package core.jdbc;

import jwp.model.Question;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionRowMapper implements RowMapper<Question> {
    @Override
    public Question mapRow(ResultSet rs) throws SQLException {
        Question question = new Question();
        question.setQuestionId(rs.getLong("questionId"));
        question.setWriter(rs.getString("writer"));
        question.setTitle(rs.getString("title"));
        question.setContents(rs.getString("contents"));
        question.setCreatedDate(rs.getTimestamp("createdDate").toLocalDateTime());
        question.setCountOfAnswer(rs.getInt("countOfAnswer"));
        return question;
    }
}