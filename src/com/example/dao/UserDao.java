package com.example.dao;

import com.example.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UserDao {

    private static final Logger LOGGER = Logger.getLogger(UserDao.class.getName());
    private Connection conn;


    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }




    public void insertUser(User user) {

        PreparedStatement pstmt = null;

        try {
            String sql = "INSERT INTO users(name,email,phone) VALUES (?,?,?); " ;
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPhone());
            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException se) {
            LOGGER.log(Level.SEVERE, "context", se);
        }  finally {
            try {
                if (pstmt != null) pstmt.close();

            } catch (SQLException se2) {
                LOGGER.log(Level.SEVERE, "context", se2);
            }
        }

    }



    public User getUserById(int userId) {
        PreparedStatement pstmt = null;
        User user = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM Users WHERE user_id  = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,userId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("user_id "));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
            }


        } catch (SQLException se) {
            LOGGER.log(Level.SEVERE, "context", se);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "context", e);
        } finally {
            try {
                if (rs != null) rs.close(); // Close ResultSet
            } catch (SQLException se1) {
                LOGGER.log(Level.SEVERE, "context", se1);
            }
            try {
                if (pstmt != null) pstmt.close();

            } catch (SQLException se2) {
                LOGGER.log(Level.SEVERE, "context", se2);
            }
        }
        return user;
    }
}
