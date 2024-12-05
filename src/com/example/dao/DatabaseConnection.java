package com.example.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {

    private static final Logger LOGGER = Logger.getLogger(DatabaseConnection.class.getName());
    private String user;
    private String password;
    private String jdbcDriver;
    private String dbUrl;


    public DatabaseConnection(String user, String password, String jdbcDriver, String dbUrl) {
        this.user = user;
        this.password = password;
        this.jdbcDriver = jdbcDriver;
        this.dbUrl = dbUrl;
    }

    public  Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbUrl, user, password);
            Class.forName(jdbcDriver);
        } catch (SQLException|ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "context", e);
        }
        return conn;
    }

    public  void disconnect(Connection conn ) {
        try {
            conn.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "context", e);
        }
    }

    public void createDb(Connection conn) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(Scripts.SQL);
            stmt.close();
        } catch(SQLException se) {
            LOGGER.log(Level.SEVERE, "context", se);
        } catch(Exception e) {
            LOGGER.log(Level.SEVERE, "context", e);
        } finally {
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
                LOGGER.log(Level.SEVERE, "context", se2);
            }
        }
    }

}
