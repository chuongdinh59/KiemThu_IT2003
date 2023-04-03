/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.converter;

/**
 *
 * @author Dinh Chuong
 */
import com.bros.quanlythuvien.entity.BookEntity;
import com.bros.quanlythuvien.entity.CategoryEntity;
import com.bros.quanlythuvien.model.BookModel;

public class BookConverter extends BaseConverter<BookModel> {

    public BookModel entityToModel(CategoryEntity category, BookEntity obj, Class<?> modelClass) {
        BookModel bookModel = super.entityToModel(obj, modelClass);
        bookModel.setCategoryValue(category.getValue());
        return bookModel;
    }

//    public BookEntity modelToEntity(BookModel bookModel) {
//        BookEntity book = super.modelToEntity(bookModel, BookEntity.class);
//        return book;
//    }

}
