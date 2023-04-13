/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.service;

import com.bros.quanlythuvien.model.CategoryModel;
import java.util.List;


/**
 *
 * @author phu nguyen
 */
public interface CategoryService {

    public List<CategoryModel> findAll();

    public CategoryModel findById(Integer id);

}
