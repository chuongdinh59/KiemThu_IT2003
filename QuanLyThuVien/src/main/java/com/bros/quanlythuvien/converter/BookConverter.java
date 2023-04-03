/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.converter;

/**
 *
 * @author Dinh Chuong
 */
import com.bros.quanlythuvien.entity.CategoryEntity;
import com.bros.quanlythuvien.model.BookModel;
import com.bros.quanlythuvien.model.SearchBookModel;
import com.bros.quanlythuvien.repository.CategoryRepository;
import com.bros.quanlythuvien.repository.impl.CategoryRepositoryImpl;
public class BookConverter extends BaseConverter<BookModel> {
    public SearchBookModel BookModelToSearchBookModel(CategoryEntity category , BookModel obj, Class<SearchBookModel> SearchBookModelClass) {
        SearchBookModel searchBookModel = (SearchBookModel)super.AToB(obj, SearchBookModelClass);
        searchBookModel.setCate(category.getValue());
        return searchBookModel;
    }
}
