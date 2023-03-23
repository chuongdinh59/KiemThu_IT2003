/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.service.impl;

import com.bros.quanlythuvien.converter.BorrowCardConverter;
import com.bros.quanlythuvien.entity.BorrowCardEntity;
import com.bros.quanlythuvien.model.BorrowCardModel;
import com.bros.quanlythuvien.repository.BorrowCardRepository;
import com.bros.quanlythuvien.repository.impl.BorrowCardRepositoryImpl;
import com.bros.quanlythuvien.service.BorrowCardService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phu nguyen
 */
public class BorrowCardServiceImpl implements BorrowCardService {

    BorrowCardRepository borrowCardRepository = new BorrowCardRepositoryImpl();
    BorrowCardConverter borrowCardConverter = new BorrowCardConverter();

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
            resultsBorrowCardModel.add(borrowCardConverter.entityToModel(entity, BorrowCardModel.class));
        }
        return resultsBorrowCardModel;
    }

    public static void main(String[] args) {
        BorrowCardService borrowCardService = new BorrowCardServiceImpl();
        List<BorrowCardModel> borrowCardModel = borrowCardService.findAll();
        for (BorrowCardModel borrow : borrowCardModel) {
        System.out.println("ID: " + borrow.getId());
        System.out.println("Reader ID: " + borrow.getReaderID());
        System.out.println("Issued Date: " + borrow.getIssuedDate());
        System.out.println("Expiry Date: " + borrow.getExpiredDate());
        }
    }
}
