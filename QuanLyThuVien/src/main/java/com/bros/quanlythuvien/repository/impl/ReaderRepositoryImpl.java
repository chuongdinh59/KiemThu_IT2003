/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.repository.impl;

import static com.bros.quanlythuvien.constant.DatabaseConstant.DB_URL;
import static com.bros.quanlythuvien.constant.DatabaseConstant.PASS;
import static com.bros.quanlythuvien.constant.DatabaseConstant.USER;
import com.bros.quanlythuvien.entity.ReaderEntity;
import com.bros.quanlythuvien.repository.ReaderRepository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phu nguyen
 */
public class ReaderRepositoryImpl extends CommonRepositoryImpl<ReaderEntity> implements ReaderRepository {

    @Override
    public List<ReaderEntity> findReaderById(Integer readerId, Integer page) {
        List<ReaderEntity> readerList = new ArrayList<>();
        String query;
        if (readerId == null) {
            query = "SELECT * FROM readers";
        } else {
            query = "SELECT * FROM readers WHERE ReaderID = ?";
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
            PreparedStatement stmt = conn.prepareStatement(query)) {
            if (readerId != null){
            stmt.setInt(1, readerId);
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ReaderEntity reader = new ReaderEntity();
                reader.setId(rs.getInt("ReaderID"));
                reader.setFullName(rs.getString("FullName"));
                reader.setGender(rs.getString("Gender"));
                reader.setDateOfBirth(rs.getDate("DateOfBirth"));
                reader.setReaderType(rs.getString("ReaderType"));
                readerList.add(reader);
                System.out.println(readerList);
            }
        } catch (SQLException e) {
        }

        return readerList;
    }

//    public static void main(String[] args) {
//        ReaderRepositoryImpl readerRepository = new ReaderRepositoryImpl();
//        List<ReaderEntity> readerList = readerRepository.findReaderById(1, null);
//        for (ReaderEntity reader : readerList) {
//            System.out.println("Reader ID: " + reader.getId());
//            System.out.println("Reader Name: " + reader.getFullName());
//            System.out.println("Reader Address: " + reader.getGender());
//            System.out.println("Reader Email: " + reader.getDateOfBirth());
//            System.out.println("Reader Email: " + reader.getReaderType());
//
//        }
//
//    }

}
