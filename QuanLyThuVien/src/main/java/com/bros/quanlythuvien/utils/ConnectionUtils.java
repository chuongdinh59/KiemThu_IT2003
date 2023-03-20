package com.bros.quanlythuvien.utils;


import com.bros.quanlythuvien.constant.DatabaseConstant;
import java.sql.Connection;
import java.sql.DriverManager;




public class ConnectionUtils {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DatabaseConstant.DB_URL, DatabaseConstant.USER, DatabaseConstant.PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}