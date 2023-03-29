/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.service.impl;

import com.bros.quanlythuvien.converter.ReaderConverter;
import com.bros.quanlythuvien.entity.ReaderEntity;
import com.bros.quanlythuvien.model.ReaderModel;
import com.bros.quanlythuvien.repository.ReaderRepository;
import com.bros.quanlythuvien.repository.impl.ReaderRepositoryImpl;
import com.bros.quanlythuvien.service.ReaderService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phu nguyen
 */
public class ReaderServiceImpl implements ReaderService {

    ReaderRepository readerRepository = new ReaderRepositoryImpl();
    ReaderConverter readerConverter = new ReaderConverter();

    @Override
    public ReaderModel findReaderById(Integer id) {
        ReaderEntity readerEntity = readerRepository.findReaderById(id);
        return readerConverter.entityToModel(readerEntity, ReaderModel.class);
    }

    @Override
    public List<ReaderModel> findAll() {
        List<ReaderEntity> borrowCardList = readerRepository.findAll(null);
        List<ReaderModel> resultsBorrowCardModel = new ArrayList<>();

        for (ReaderEntity entity : borrowCardList) {
            resultsBorrowCardModel.add(readerConverter.entityToModel(entity, ReaderModel.class));
        }
        return resultsBorrowCardModel;
    }

//    public static void main(String[] args) {
//        ReaderService readerService = new ReaderServiceImpl();
//        ReaderModel reader = readerService.findReaderById(6);
//             System.out.println("name ne: " + reader.getFullname());
//
//    }
////    List<ReaderModel> readerList = readerService.findAll();
//
////    for (ReaderModel reader : readerList) {
//        System.out.println("Reader ID: " + reader.getId());
//        System.out.println("Reader Name: " + reader.getFullname());
//        System.out.println("Reader Gender: " + reader.getGender());
//        System.out.println("Reader Date of Birth: " + reader.getDateOfBirth());
//        System.out.println("Reader Type: " + reader.getReaderType());
//        System.out.println("=======================");
////    }
//    }

}
