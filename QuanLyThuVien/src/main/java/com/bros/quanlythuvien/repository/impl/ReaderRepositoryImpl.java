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
        if (readerId == null) {
            return super.findAll();
        }
        List<ReaderEntity> list = new ArrayList<>();
        ReaderEntity r = super.findById(readerId);
        if (r != null) {
            list.add(r);
        }
        return list;
    }

    public static void main(String[] args) {
        ReaderRepositoryImpl readerRepository = new ReaderRepositoryImpl();
        List<ReaderEntity> readerList = readerRepository.findReaderById(1, null);
        for (ReaderEntity reader : readerList) {
            System.out.println("Reader ID: " + reader.getId());
            System.out.println("Reader Name: " + reader.getFullName());
            System.out.println("Reader Address: " + reader.getGender());
            System.out.println("Reader Email: " + reader.getDateOfBirth());
            System.out.println("Reader Email: " + reader.getReaderType());

        }

    }

}
