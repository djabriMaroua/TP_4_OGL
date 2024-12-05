package com.example;

import com.example.dao.*;
import com.example.entity.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
public class Home {

    private static final Logger LOGGER = Logger.getLogger(Home.class.getName());
    public static void main(String[] args) throws ParseException {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("Failed to load configuration: " + e.getMessage());
            return;
        }

        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");
        String jdbcDriver = properties.getProperty("db.driver");
        String dbUrl = properties.getProperty("db.url");

        DatabaseConnection db = new DatabaseConnection(user, password, jdbcDriver, dbUrl);
        Connection connection = db.connect();
        try {
            if (args.length > 0) {

                // insert Parking
                Parking parking = new Parking();
                parking.setName("Parking 1");
                parking.setAddress("Alger");
                parking.setCapacity(30);
                ParkingDao parkingDao = new ParkingDao();
                parkingDao.setConn(connection);
                parkingDao.insertParking(parking);
                // insert Parking Place
                ParkingPlaceDao parkingPlaceDao = new ParkingPlaceDao();
                parkingPlaceDao.setConn(connection);
                ParkingPlace parkingPlace = new ParkingPlace();
                parkingPlace.setIdPlace(1);
                parking.setParkingId(1);
                parkingPlace.setParking(parking);
                parkingPlace.setPlaceName("F5");
                parkingPlace.setPlaceStatus(PlaceStatus.AVAILABLE);
                parkingPlaceDao.insertParkingPlace(parkingPlace);
                // insert user
                UserDao userDao = new UserDao();
                userDao.setConn(connection);
                User user1 = new User();
                user1.setUserId(1);
                user1.setName("Ali");
                user1.setPhone("055555555");
                user1.setEmail("ali@gmail.com");
                userDao.insertUser(user1);
                // insert Reservation
                ReservationDao reservationDao = new ReservationDao();
                reservationDao.setConn(connection);
                Reservation reservation = new Reservation();
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
                reservation.setStatus(ReservationStatus.PENDING);
                reservation.setUser(user1);
                reservation.setParkingPlace(parkingPlace);
                reservation.setStartTime(dateFormat.parse("22-10-2023 10:00"));
                reservation.setEndTime(dateFormat.parse("22-10-2023 11:45"));
                reservationDao.insertReservation(reservation);
            }
        }
        catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Arguments must be an integer: {0}", e.getMessage());
            System.exit(1);
        }
    }
}
