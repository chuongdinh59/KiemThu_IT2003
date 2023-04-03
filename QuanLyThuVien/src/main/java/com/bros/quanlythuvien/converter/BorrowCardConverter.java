/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.converter;

import com.bros.quanlythuvien.entity.BorrowCardEntity;
import com.bros.quanlythuvien.entity.ReaderEntity;
import com.bros.quanlythuvien.model.BorrowCardModel;
import com.bros.quanlythuvien.utils.DateUtils;

/**
 *
 * @author ADMIN
 */
public class BorrowCardConverter extends BaseConverter<BorrowCardConverter> {

    public BorrowCardModel entityToModel(ReaderEntity reader,BorrowCardEntity entity, Class<BorrowCardModel> tClass) {
        BorrowCardModel model = modelMapper.map(entity, tClass);
        model.setIssuedDate(DateUtils.convertDateToString(entity.getIssuedDate()));
        model.setExpiredDate(DateUtils.convertDateToString(entity.getExpiredDate()));
        model.setFullName(reader.getFullName());
        return model;
    }
     public BorrowCardModel entityToModel(BorrowCardEntity entity, Class<BorrowCardModel> tClass) {
        BorrowCardModel model = modelMapper.map(entity, tClass);
        model.setIssuedDate(DateUtils.convertDateToString(entity.getIssuedDate()));
        model.setExpiredDate(DateUtils.convertDateToString(entity.getExpiredDate()));
        return model;
    }
}
