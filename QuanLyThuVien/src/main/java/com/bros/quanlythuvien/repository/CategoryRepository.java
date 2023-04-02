/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.repository;

import com.bros.quanlythuvien.entity.CategoryEntity;
import java.util.List;

/**
 *
 * @author phu nguyen
 */
public interface CategoryRepository extends CommonRepository<CategoryEntity>{
        public List<CategoryEntity> findAll(Integer page);

        public CategoryEntity findById (Integer id);

}
