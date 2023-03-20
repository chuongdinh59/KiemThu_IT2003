/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.service.impl;

import com.bros.quanlythuvien.converter.ReaderConverter;
import com.bros.quanlythuvien.entity.BookEntity;
import com.bros.quanlythuvien.entity.ReaderEntity;
import com.bros.quanlythuvien.model.BookModel;
import com.bros.quanlythuvien.model.ReaderModel;
import com.bros.quanlythuvien.repository.ReaderRepository;
import com.bros.quanlythuvien.repository.impl.ReaderRepositoryImpl;
import com.bros.quanlythuvien.service.BookService;
import com.bros.quanlythuvien.service.ReaderService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author phu nguyen
 */
public class ReaderServiceImpl implements ReaderService {

    ReaderRepository readerRepository = new ReaderRepositoryImpl();
    ReaderConverter readerConverter = new ReaderConverter();

    @Override
    public List<ReaderModel> findReaderById(Integer id, Integer page) {

        List<ReaderEntity> resultsReaderEntity = readerRepository.findReaderById(id, page);

        List<ReaderModel> resultsReaderModel = new ArrayList<>();

        for (ReaderEntity entity : resultsReaderEntity) {
            resultsReaderModel.add(readerConverter.entityToModel(entity, ReaderModel.class));
        }
        return resultsReaderModel;
    }

//    public static void main(String[] args) {
//    ReaderService readerService = new ReaderServiceImpl();
//    List<ReaderModel> readerList = readerService.findReaderById(null, null);
//    for (ReaderModel reader : readerList) {
//        System.out.println("Reader ID: " + reader.getId());
//        System.out.println("Reader Name: " + reader.getFullName());
//        System.out.println("Reader Gender: " + reader.getGender());
//        System.out.println("Reader Date of Birth: " + reader.getDateOfBirth());
//        System.out.println("Reader Type: " + reader.getReaderType());
//        System.out.println("=======================");
//    }
//}

}
