/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.service;

import com.bros.quanlythuvien.model.ReaderModel;
import java.util.List;

/**
 *
 * @author phu nguyen
 */
public interface ReaderService {
       // tên sách, tên tác giả,
    //năm xuất bản, danh mục.?
      public List<ReaderModel> findReaderById(Integer id, Integer page);
}