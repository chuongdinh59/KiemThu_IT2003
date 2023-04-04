/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.converter;

import com.bros.quanlythuvien.entity.LoanSlipEntity;
import com.bros.quanlythuvien.model.LoanSlipModel;
import com.bros.quanlythuvien.utils.DateUtils;
import org.modelmapper.ModelMapper;

/**
 *
 * @author ADMIN
 */
public class LoanslipConverter {
     public static ModelMapper modelMapper = new ModelMapper();

    public LoanSlipModel entityToModel(LoanSlipEntity entity, Class<LoanSlipModel> tClass) {
        LoanSlipModel model = modelMapper.map(entity, tClass);
        model.setBorrowedDate(DateUtils.convertDateToString(entity.getBorrowedDate()));
        model.setExpirationDate(DateUtils.convertDateToString(entity.getExpirationDate()));
        return model;
    }
}
