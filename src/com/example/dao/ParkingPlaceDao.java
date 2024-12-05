package com.example.dao;

import com.example.entity.ParkingPlace;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParkingPlaceDao {

    private static final Logger LOGGER = Logger.getLogger(ParkingPlaceDao.class.getName());
    private Connection conn;


    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }



    public void insertParkingPlace(ParkingPlace parkingPlace) {

        PreparedStatement pstmt = null;

        try {
            String sql = "INSERT INTO parking_places(place_name,place_status,parking_id) VALUES (?,?,?); " ;
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, parkingPlace.getPlaceName());
            pstmt.setString(2, parkingPlace.getPlaceStatus().name());
            pstmt.setInt(3, parkingPlace.getParking().getParkingId());
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



}
