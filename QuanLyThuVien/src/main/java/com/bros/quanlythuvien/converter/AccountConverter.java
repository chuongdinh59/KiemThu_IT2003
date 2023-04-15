/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.converter;

/**
 *
 * @author ADMIN
 */
import com.bros.quanlythuvien.entity.AccountEntity;
import com.bros.quanlythuvien.model.AccountModel;
import org.modelmapper.ModelMapper;
public class AccountConverter {
    public static ModelMapper modelMapper = new ModelMapper();

    public AccountModel entityToModel(AccountEntity entity, Class<AccountModel> tClass) {
        AccountModel model = modelMapper.map(entity, tClass);
        return model;
    }
}
