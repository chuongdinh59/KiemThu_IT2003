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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

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
            CategoryEntity category = categoryRepository.findById(entity.getCategoryID());
            BookModel bookModel = bookConverter.entityToModel(category, entity, BookModel.class);
            resultsBookModel.add(bookModel);
        }
        return resultsBookModel;
    }


    
    @Override
    public Map<String,Object> getSearchMap(String strTitle, String strAuthor, Integer cateID, String strPublish){
        Map<String,Object> searchMap = new HashMap<>();
        if(ValidateUtils.isValid(strTitle)) {
            searchMap.put("BookTitle", strTitle);
        }
        if(ValidateUtils.isValid(strAuthor)) {
            searchMap.put("Author", strAuthor);
        }
        if(ValidateUtils.isValid(cateID)) {
            searchMap.put("CategoryID", cateID);
        }
        if(ValidateUtils.isValid(strPublish)) {
            searchMap.put("PublicationYear", strPublish);
        }
        return searchMap;
    }
    
//    public BookModel get1Book(Integer id,String title,String author,String description, Integer publicationYear,String publicationPlace,Integer categoryID,String location){
//        BookModel book = new BookModel(id, title, author, description, publicationYear, publicationPlace, categoryID, location);
//        return book;
//    }
    
    @Override
    public  BookModel getBook(TextField id,TextField title,TextField author,TextField description
            ,TextField publicationPlace,TextField publicationYear ,ComboBox<String> category,TextField location){
        BookModel book = new BookModel();
        book.setId(Integer.valueOf(id.getText()));
        book.setTitle(title.getText());
        book.setAuthor(author.getText());
        book.setDescription(description.getText());
        System.out.print(publicationYear.getText());
        book.setPublicationYear(Integer.valueOf(publicationYear.getText()));
        book.setPublicationPlace(publicationPlace.getText());
        book.setCategoryValue(category.getValue());
        book.setLocation(location.getText());
        return book;
    }
}
