/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.service.impl;

import com.bros.quanlythuvien.converter.BookConverter;
import com.bros.quanlythuvien.entity.BookEntity;
import com.bros.quanlythuvien.entity.CategoryEntity;
import com.bros.quanlythuvien.model.BookModel;
import com.bros.quanlythuvien.repository.BookRepository;
import com.bros.quanlythuvien.repository.CategoryRepository;
import com.bros.quanlythuvien.repository.impl.BookRepositoryImpl;
import com.bros.quanlythuvien.repository.impl.CategoryRepositoryImpl;
import com.bros.quanlythuvien.service.BookService;
import com.bros.quanlythuvien.utils.MessageBoxUtils;
import com.bros.quanlythuvien.utils.ValidateUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.control.Alert;

/**
 *
 * @author Dinh Chuong
 */
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository = new BookRepositoryImpl();
    private BookConverter bookConverter = new BookConverter();
    private CategoryRepository categoryRepository = new CategoryRepositoryImpl();

    @Override
    public List<BookModel> findAll(Integer page) {
        List<BookEntity> bookList = bookRepository.findAll(null);
        List<BookModel> resultsBookModel = new ArrayList<>();

        for (BookEntity entity : bookList) {
            CategoryEntity categoryEntity = categoryRepository.findById(entity.getCategoryID());
            resultsBookModel.add(bookConverter.entityToModel(categoryEntity,entity, BookModel.class));
        }
        return resultsBookModel;
    }

    @Override
    public BookModel findById(Integer id) {
        BookEntity bookEntiry = bookRepository.findById(id);
        CategoryEntity categoryEntity = categoryRepository.findById(bookEntiry.getCategoryID());
        return bookConverter.entityToModel(categoryEntity ,bookRepository.findById(id), BookModel.class);
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
    public Map<String, Object> getSearchMap(String strTitle, String strAuthor, Integer cateID, String strPublish) {
        Map<String, Object> searchMap = new HashMap<>();
        if (ValidateUtils.isValid(strTitle)) {
            searchMap.put("BookTitle", strTitle);
        }
        if (ValidateUtils.isValid(strAuthor)) {
            searchMap.put("Author", strAuthor);
        }
        if (ValidateUtils.isValid(cateID)) {
            searchMap.put("CategoryID", cateID);
        }
        if (ValidateUtils.isValid(strPublish)) {
            try {
                searchMap.put("PublicationYear", Integer.valueOf(strPublish));
            } catch (NumberFormatException e) {
                MessageBoxUtils.AlertBox("ERROR", "Vui lòng nhập số", Alert.AlertType.ERROR);
            }
        }
        return searchMap;
    }

    @Override
    public void updateBook(BookModel book) {
        boolean rs = bookRepository.updateBook(book);
        if (rs) {
            MessageBoxUtils.AlertBox("INFORMATION", "Sửa đổi dữ liệu thành công", Alert.AlertType.INFORMATION);
        } else {
            MessageBoxUtils.AlertBox("ERROR", "Sửa đổi dữ liệu thất bại", Alert.AlertType.ERROR);
        }
    }

    @Override
    public void inserBook(BookModel book) {
        boolean rs = bookRepository.insertBook(book);
        if (rs) {
            MessageBoxUtils.AlertBox("INFORMATION", "Thêm dữ liệu thành công", Alert.AlertType.INFORMATION);
        } else {
            MessageBoxUtils.AlertBox("ERROR", "Thêm dữ liệu thất bại", Alert.AlertType.ERROR);
        }
    }

    @Override
    public void deleteBook(Integer id) {
        boolean rs = bookRepository.deleteBook(id);
        if (rs) {
            MessageBoxUtils.AlertBox("INFORMATION", "Xóa dữ liệu thành công", Alert.AlertType.INFORMATION);
        } else {
            MessageBoxUtils.AlertBox("ERROR", "Xóa dữ liệu thất bại", Alert.AlertType.ERROR);
        }
    }

}
