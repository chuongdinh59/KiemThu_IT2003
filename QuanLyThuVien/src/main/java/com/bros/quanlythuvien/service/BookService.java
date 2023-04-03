/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.service;

import com.bros.quanlythuvien.model.BookModel;
import com.bros.quanlythuvien.model.SearchBookModel;
import java.util.List;
import java.util.Map;

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
    
    public Map<String,Object> getSearchMap(String strTitle, String strAuthor, Integer cateID, String strPublish);

    
}
    