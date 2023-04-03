/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.service;

import com.bros.quanlythuvien.model.BookModel;
import java.util.List;
import java.util.Map;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 *
 * @author Dinh Chuong
 */
public interface BookService {

    // tên sách, tên tác giả,
    //năm xuất bản, danh mục.?
    List<BookModel> findBooks(Map<String, Object> searchMap, Integer page);

    BookModel findById(Integer id);

    public List<BookModel> findAll( Integer page);

    public Map<String,Object> getSearchMap(String strTitle, String strAuthor, Integer strCate, String strPublish);

    public  BookModel getBook(TextField id,TextField title,TextField author,TextField description,TextField publicationYear,TextField publicationPlace,ComboBox<String> category,TextField location);
}
    