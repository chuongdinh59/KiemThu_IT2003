/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.service.impl;

import com.bros.quanlythuvien.converter.BorrowCardConverter;
import com.bros.quanlythuvien.entity.BorrowCardEntity;
import com.bros.quanlythuvien.entity.ReaderEntity;
import com.bros.quanlythuvien.model.BorrowCardModel;
import com.bros.quanlythuvien.repository.BorrowCardRepository;
import com.bros.quanlythuvien.repository.ReaderRepository;
import com.bros.quanlythuvien.repository.impl.BorrowCardRepositoryImpl;
import com.bros.quanlythuvien.repository.impl.ReaderRepositoryImpl;
import com.bros.quanlythuvien.service.BorrowCardService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phu nguyen
 */
public class BorrowCardServiceImpl implements BorrowCardService {

    private BorrowCardRepository borrowCardRepository = new BorrowCardRepositoryImpl();
    private BorrowCardConverter borrowCardConverter = new BorrowCardConverter();
    private ReaderRepository readerRepository = new ReaderRepositoryImpl();
    @Override
    public BorrowCardModel findBorrowCardByRID(int ReaderID) {
        BorrowCardEntity borrowCardEntity = borrowCardRepository.findBorrowCardByRID(ReaderID);
        if (borrowCardEntity == null){return null;}
        return borrowCardConverter.entityToModel(borrowCardEntity, BorrowCardModel.class);
    }
    @Override
    public List<BorrowCardModel> findAll() {
        List<BorrowCardEntity> borrowCardList = borrowCardRepository.findAll(null);
        List<BorrowCardModel> resultsBorrowCardModel = new ArrayList<>();
        for (BorrowCardEntity entity : borrowCardList) {
            ReaderEntity reader = readerRepository.findById(entity.getReaderID());
            resultsBorrowCardModel.add(borrowCardConverter.entityToModel(reader,entity, BorrowCardModel.class));
        }
        return resultsBorrowCardModel;
    }
    
    @Override
    public boolean createBorrowCard(Integer id) {
        boolean rs = borrowCardRepository.createBorrowCard(id);
        return rs;
    }


}
