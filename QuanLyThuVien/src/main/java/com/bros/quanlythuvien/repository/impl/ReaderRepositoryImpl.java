/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.repository.impl;


import com.bros.quanlythuvien.entity.ReaderEntity;
import com.bros.quanlythuvien.repository.ReaderRepository;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phu nguyen
 */
public class ReaderRepositoryImpl extends CommonRepositoryImpl<ReaderEntity> implements ReaderRepository {

    @Override
    public ReaderEntity findReaderById(Integer readerId ) {
        ReaderEntity r = super.findById(readerId);
        return r;
    }
    
    @Override
     public List<ReaderEntity> findAll(Integer page){
         List<ReaderEntity> r = super.findAll();
         return r;
     }

    public static void main(String[] args) {
        ReaderRepositoryImpl readerRepository = new ReaderRepositoryImpl();
        List<ReaderEntity> readerList = readerRepository.findAll();
//        ReaderEntity reader = readerRepository.findReaderById(1, null);

        for (ReaderEntity reader : readerList) {
            System.out.println("Reader ID: " + reader.getId());
            System.out.println("Reader Name: " + reader.getFullName());
            System.out.println("Reader Address: " + reader.getGender());
            System.out.println("Reader Email: " + reader.getDateOfBirth());
            System.out.println("Reader Email: " + reader.getReaderType());

        }

    }

}
