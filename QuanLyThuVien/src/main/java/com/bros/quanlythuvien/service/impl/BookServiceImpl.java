/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.service.impl;

import com.bros.quanlythuvien.converter.BookConverter;
import com.bros.quanlythuvien.entity.BookEntity;
import com.bros.quanlythuvien.entity.CategoryEntity;
import com.bros.quanlythuvien.model.BookModel;
import com.bros.quanlythuvien.model.CategoryModel;
import com.bros.quanlythuvien.model.SearchBookModel;
import com.bros.quanlythuvien.repository.BookRepository;
import com.bros.quanlythuvien.repository.CategoryRepository;
import com.bros.quanlythuvien.repository.impl.BookRepositoryImpl;
import com.bros.quanlythuvien.repository.impl.CategoryRepositoryImpl;
import com.bros.quanlythuvien.service.BookService;
import com.bros.quanlythuvien.service.CategoryService;
import com.bros.quanlythuvien.utils.ValidateUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Dinh Chuong
 */
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository = new BookRepositoryImpl();
    private BookConverter bookConverter = new BookConverter();
    private CategoryRepository categoryRepository = new CategoryRepositoryImpl();
    @Override
    public List<BookModel> findAll(Integer page){
           List<BookEntity> bookList = bookRepository.findAll(null);
        List<BookModel> resultsBookModel = new ArrayList<>();

        for (BookEntity entity : bookList) {
            resultsBookModel.add(bookConverter.entityToModel(entity, BookModel.class));
        }
        return resultsBookModel;
    }

    @Override
    public BookModel findById(Integer id) {
        return null;
    }

    @Override
    public List<BookModel> findBooks(Map<String, Object> searchMap, Integer page) {

        List<BookEntity> resultsBookEntity = bookRepository.findBooks(searchMap, page);

        List<BookModel> resultsBookModel = new ArrayList<>();

        for (BookEntity entity : resultsBookEntity) {
            BookModel bookModel = bookConverter.entityToModel(entity, BookModel.class);
            resultsBookModel.add(bookModel);
        }
        
        return resultsBookModel;
    }

    @Override
    public List<SearchBookModel> getSearchBookList(Map<String, Object> searchMap , Integer page) {
        List<BookModel> bookList = findBooks(searchMap, null);
        List<SearchBookModel> searchBookList = new ArrayList<>();
        for (BookModel book : bookList) {
            CategoryEntity category = categoryRepository.findById(book.getCategoryID());
            SearchBookModel searchBookModel = bookConverter
                    .BookModelToSearchBookModel( category, book, SearchBookModel.class);
            searchBookList.add(searchBookModel);
        }
        return searchBookList;
    }
    
    @Override
    public Map<String,Object> getSearchMap(String strTitle, String strAuthor, String strCate, String strPublish){
        Map<String,Object> searchMap = new HashMap<>();
        if(ValidateUtils.isValid(strTitle)) {
            searchMap.put("BookTitle", strTitle);
        }
        if(ValidateUtils.isValid(strAuthor)) {
            searchMap.put("Author", strAuthor);
        }
        if(ValidateUtils.isValid(strCate)) {
            searchMap.put("CategoryID", strPublish);
        }
        if(ValidateUtils.isValid(strPublish)) {
            searchMap.put("PublicationYear", strTitle);
        }
        return searchMap;
    }
}
