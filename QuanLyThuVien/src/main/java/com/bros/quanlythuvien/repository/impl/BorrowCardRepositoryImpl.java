/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.repository.impl;

import com.bros.quanlythuvien.entity.BorrowCardEntity;
import com.bros.quanlythuvien.repository.BorrowCardRepository;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class BorrowCardRepositoryImpl extends CommonRepositoryImpl<BorrowCardEntity> implements BorrowCardRepository {

    @Override
    public BorrowCardEntity findBorrowCardByRID(int ReaderID) {
        BorrowCardEntity b = super.findByRId(ReaderID);
        if (b == null) {
            return null;
        }
        return b;
    }

    @Override
    public List<BorrowCardEntity> findAll(Integer page) {
        List<BorrowCardEntity> b = super.findAll();
        return b;
    }

//    public static void main(String[] args) {
//        BorrowCardRepositoryImpl borrowCardRepository = new BorrowCardRepositoryImpl();
//        BorrowCardEntity borrowCard = borrowCardRepository.findBorrowCardByRID(3);
//        System.out.println("ID: " + borrowCard.getId());
//        System.out.println("Reader ID: " + borrowCard.getReaderID());
//        System.out.println("Issued Date: " + borrowCard.getIssuedDate());
//        System.out.println("Expiry Date: " + borrowCard.getExpiredDate());
//
//    }
}
