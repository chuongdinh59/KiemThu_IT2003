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
import java.util.Map;
import javafx.scene.control.ComboBox;

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
        return categoryConverter.entityToModel(categoryEntity, CategoryModel.class);
    }
    
    @Override
     public void loadCate(ComboBox <String> availableBooks_category,Map<Integer, String> categoriesMap) {
        availableBooks_category.setPromptText("Chọn thể loại");
        availableBooks_category.getItems().add(0, "Chọn thể loại");
        categoriesMap.clear();

        try {
            List<CategoryModel> categories = findAll();
            for (CategoryModel c : categories) {
                categoriesMap.put(c.getCategoryID(), c.getValue());
            }
            availableBooks_category.getItems().addAll(categoriesMap.values());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
