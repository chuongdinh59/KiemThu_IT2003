/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.converter;

/**
 *
 * @author phu nguyen
 */
import com.bros.quanlythuvien.entity.ReaderEntity;
import com.bros.quanlythuvien.model.ReaderModel;
import com.bros.quanlythuvien.utils.DateUtils;
import org.modelmapper.ModelMapper;
public class ReaderConverter {
    public static ModelMapper modelMapper = new ModelMapper();

    public ReaderModel entityToModel(ReaderEntity entity, Class<ReaderModel> tClass) {
        ReaderModel model = modelMapper.map(entity, tClass);
        model.setDateOfBirth(DateUtils.convertDateToString(entity.getDateOfBirth()));
        return model;
    }
}
