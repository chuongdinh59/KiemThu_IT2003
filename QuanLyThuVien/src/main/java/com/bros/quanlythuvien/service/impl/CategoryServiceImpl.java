/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.service.impl;

import com.bros.quanlythuvien.converter.CategoryConverter;
import com.bros.quanlythuvien.entity.CategoryEntity;
import com.bros.quanlythuvien.model.CategoryModel;
import com.bros.quanlythuvien.repository.CategoryRepository;
import com.bros.quanlythuvien.repository.impl.CategoryRepositoryImpl;
import com.bros.quanlythuvien.service.CategoryService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phu nguyen
 */
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository = new CategoryRepositoryImpl();
    CategoryConverter categoryConverter = new CategoryConverter();

    @Override
    public List<CategoryModel> findAll() {
        List<CategoryEntity> cateList = categoryRepository.findAll(null);
        List<CategoryModel> resultscateModel = new ArrayList<>();

        for (CategoryEntity entity : cateList) {
            resultscateModel.add(categoryConverter.entityToModel(entity, CategoryModel.class));
        }
        return resultscateModel;
    }

    @Override
    public CategoryModel findById(Integer id){
        CategoryEntity categoryEntity = categoryRepository.findById(id);
        if(categoryEntity == null) return null;
        return categoryConverter.entityToModel(categoryEntity, CategoryModel.class);
    }
    
    
}


