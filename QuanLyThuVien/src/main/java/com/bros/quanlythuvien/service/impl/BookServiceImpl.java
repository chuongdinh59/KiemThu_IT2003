/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.service.impl;

import com.bros.quanlythuvien.converter.BookConverter;
import com.bros.quanlythuvien.entity.BookEntity;
import com.bros.quanlythuvien.model.BookModel;
import com.bros.quanlythuvien.repository.BookRepository;
import com.bros.quanlythuvien.repository.impl.BookRepositoryImpl;
import com.bros.quanlythuvien.service.BookService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dinh Chuong
 */
public class BookServiceImpl implements BookService {

    BookRepository bookRepository = new BookRepositoryImpl();
    BookConverter bookConverter = new BookConverter(); 

    @Override
    public BookModel findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<BookModel> findBooks(Map<String, Object> searchMap, Integer page) {

        List<BookEntity> resultsBookEntity = bookRepository.findBooks(searchMap, page);
        
        List<BookModel> resultsBookModel = new ArrayList<>();
        
        for (BookEntity entity : resultsBookEntity) {
            resultsBookModel.add(bookConverter.entityToModel(entity, BookEntity.class));
        }
        
        return resultsBookModel;
    }
    
    
    public static void main(String[] args) {
        BookService bookService = new BookServiceImpl();
        bookService.findBooks(null, null);
    }

}
