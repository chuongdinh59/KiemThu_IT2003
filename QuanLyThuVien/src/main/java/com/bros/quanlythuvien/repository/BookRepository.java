/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.repository;

import com.bros.quanlythuvien.entity.BookEntity;
import com.bros.quanlythuvien.model.BookModel;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dinh Chuong
 */
public interface BookRepository extends CommonRepository<BookEntity>{
    List<BookEntity> findBooks(Map<String, Object> searchMap, Integer page);
    
    List<BookEntity> findAll(Integer page);

    public boolean updateBook(BookModel book);
    
    public BookModel insertBook(BookModel book);
    
    public boolean deleteBook(Integer id);
    
    public boolean saveImage(Integer id, String url);
    
    public String getImageById(Integer id);
}
