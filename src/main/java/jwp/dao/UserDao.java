package jwp.dao;

import core.jdbc.ConnectionManager;
import jwp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public void insert(User user) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionManager.getConnection();
            String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null)
                pstmt.close();
            if (conn != null)
                conn.close();
        }
    }

    public void update(User user) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionManager.getConnection();
            String sql = "UPDATE USERS SET password =?, name =?, email =? WHERE userId =?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getUserId());
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null)
                pstmt.close();
            if (conn != null)
                conn.close();
        }
    }

    public void delete(User user) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionManager.getConnection();
            String sql = "DELETE FROM USERS WHERE userId =?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUserId());
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null)
                pstmt.close();
            if (conn != null)
                conn.close();
        }
    }

    public List<User> findAll() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<>();
        try {
            conn = ConnectionManager.getConnection();
            String sql = "SELECT * FROM USERS";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getString("userId"), rs.getString("password"),
                        rs.getString("name"), rs.getString("email"));
                users.add(user);
            }
        } finally {
            if (conn != null)
                conn.close();
            if (pstmt != null)
                pstmt.close();
            if (rs != null)
                rs.close();
        }
        return users;
    }

    public User findByUserId(String userId) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;
        try {
            conn = ConnectionManager.getConnection();
            String sql = "SELECT * FROM USERS WHERE userId =?";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString("userId"), rs.getString("password"),
                        rs.getString("name"), rs.getString("email"));
            }
        } finally {
            if (conn != null)
                conn.close();
            if (pstmt != null)
                pstmt.close();
            if (rs != null)
                rs.close();
        }
        return user;
    }
}
