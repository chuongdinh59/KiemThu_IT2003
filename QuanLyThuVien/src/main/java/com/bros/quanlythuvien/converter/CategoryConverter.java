/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.converter;

import static com.bros.quanlythuvien.converter.BaseConverter.modelMapper;
import com.bros.quanlythuvien.entity.CategoryEntity;
import com.bros.quanlythuvien.model.CategoryModel;
import com.bros.quanlythuvien.repository.CategoryRepository;
import com.bros.quanlythuvien.repository.impl.CategoryRepositoryImpl;

/**
 *
 * @author phu nguyen
 */
public class CategoryConverter {

    private final static CategoryRepository categoryRepository = new CategoryRepositoryImpl();

    public CategoryModel entityToModel(CategoryEntity entity, Class<CategoryModel> tClass) {
        CategoryModel model = modelMapper.map(entity, tClass);
//        model.setDateOfBirth(DateUtils.convertDateToString(entity.getDateOfBirth()));
        return model;
    }
}
