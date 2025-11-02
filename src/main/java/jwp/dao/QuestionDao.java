package jwp.dao;

import core.jdbc.ConnectionManager;
import jwp.model.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDao {
    public List<Question> findAll() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Question> questions = new ArrayList<>();

        try{
            conn = ConnectionManager.getConnection();
            String sql = "SELECT * FROM QUESTIONS";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            //while문으로 rs 순회하며 컬럼 값 가져옴
            while(rs.next()){
                Question qs = new Question(
                        rs.getLong("questionId"),
                        rs.getString("writer"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getString("createdDate"),
                        rs.getInt("countOfAnswer"));
                questions.add(qs); //만든 question 객체 list에 추가
            }

        } finally {
            if(pstmt!=null)
                pstmt.close();
            if(conn!=null)
                conn.close();
            if(rs!=null)
                rs.close();
        }
        return questions;
    }
}
