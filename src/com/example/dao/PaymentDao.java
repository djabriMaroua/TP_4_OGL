package com.example.dao;

import com.example.entity.Payment;

import java.sql.Connection;

public class PaymentDao {


    private Connection conn;


    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }




    public void insertPayment(Payment payment) {
        throw new UnsupportedOperationException("Not supported yet.");
    }



}
