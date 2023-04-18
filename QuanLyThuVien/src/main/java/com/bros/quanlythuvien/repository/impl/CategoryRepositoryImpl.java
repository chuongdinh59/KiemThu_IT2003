/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.repository.impl;

import com.bros.quanlythuvien.entity.CategoryEntity;
import com.bros.quanlythuvien.repository.CategoryRepository;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class CategoryRepositoryImpl extends CommonRepositoryImpl<CategoryEntity> implements CategoryRepository {

    @Override
    public List<CategoryEntity> findAll(Integer page) {
        List<CategoryEntity> r = super.findAll();
        return r;
    }

    @Override
    public CategoryEntity findById(Integer id){
        CategoryEntity c = super.findById(id);
        return c;
    }
}
