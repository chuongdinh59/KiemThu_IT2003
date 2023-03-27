/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.service;


import com.bros.quanlythuvien.model.BorrowCardModel;
import java.util.List;

/**
 *
 * @author phu nguyen
 */
public interface BorrowCardService {
    public BorrowCardModel findBorrowCardByRID(int ReaderID);
    public List<BorrowCardModel> findAll();
}
