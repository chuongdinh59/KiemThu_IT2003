/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.repository;

import com.bros.quanlythuvien.entity.BorrowCardEntity;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface BorrowCardRepository extends CommonRepository<BorrowCardEntity> {

    BorrowCardEntity findBorrowCardByRID(int ReaderID);

    public List<BorrowCardEntity> findAll(Integer page);
        public boolean createBorrowCard(Integer id);

}
