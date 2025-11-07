package jwp.dao;

import core.jdbc.ConnectionManager;
import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Question;
import jwp.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestionDao {
//    private final JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate<>();
private final EntityManager em;

    @Transactional
    public List<Question> findAll() throws SQLException {
        return em.createQuery("select q from Question q", Question.class).getResultList();

//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        List<Question> questions = new ArrayList<>();
//
//        try{
//            conn = ConnectionManager.getConnection();
//            String sql = "SELECT * FROM QUESTIONS";
//            pstmt = conn.prepareStatement(sql);
//            rs = pstmt.executeQuery();
//
//            //while문으로 rs 순회하며 컬럼 값 가져옴
//            while(rs.next()){
//                Question qs = new Question(
//                        rs.getLong("questionId"),
//                        rs.getString("writer"),
//                        rs.getString("title"),
//                        rs.getString("contents"),
//                        rs.getTimestamp("createdDate"),
//                        rs.getInt("countOfAnswer"));
//                questions.add(qs); //만든 question 객체 list에 추가
//            }
//
//        } finally {
//            if(pstmt!=null)
//                pstmt.close();
//            if(conn!=null)
//                conn.close();
//            if(rs!=null)
//                rs.close();
//        }
//        return questions;
    }

    @Transactional
    public Question insert(Question question) throws SQLException{
        em.persist(question);
        return question;
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//
//        try {
//            conn = ConnectionManager.getConnection();
////            String sql = "INSERT INTO QUESTIONS VALUES (?, ?, ?)";
//            String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, question.getWriter());
//            pstmt.setString(2, question.getTitle());
//            pstmt.setString(3, question.getContents());
//            pstmt.executeUpdate();
//        } finally{
//            if(pstmt!=null)
//                pstmt.close();
//            if(conn!=null)
//                conn.close();
//        }
//        return question;
    }

    @Transactional
    public Question findByQuestionId(Long questionId) throws SQLException {
        return em.find(Question.class, questionId);
//        String sql = "SELECT * FROM QUESTIONS WHERE questionId = ?";
//        PreparedStatementSetter ps = pstmt -> {
//            pstmt.setLong(1, questionId);
//        };
//        RowMapper<Question> rowMapper = rs -> new Question(
//                rs.getLong("questionId"),
//                rs.getString("writer"),
//                rs.getString("title"),
//                rs.getString("contents"),
//                rs.getTimestamp("createdDate"),
//                rs.getInt("countOfAnswer")
//        );
//        return jdbcTemplate.queryForObject(sql, ps, rowMapper);
    }
}
