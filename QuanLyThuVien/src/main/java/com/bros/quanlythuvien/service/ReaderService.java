/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.service;

import com.bros.quanlythuvien.model.BookModel;
import com.bros.quanlythuvien.model.ReaderModel;
import java.util.List;
import java.util.Map;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author phu nguyen
 */
public interface ReaderService {

    public ReaderModel findById(Integer id);

    public List<ReaderModel> findAll();

//    public void loadCate(ComboBox<String> RsearchBook_category, Map<Integer, String> categoriesMap);
//2
    public int checkReader(Integer id);

//    public void loadReaderColumn(TableView<ReaderModel> infoCustomerTB);
//3
//    public void loadGender(ComboBox<String> infomation_gender);
//2
    public boolean updateReader(ReaderModel reader);

//    public ReaderModel createReaderModel(Integer readerId, TextField infomation_name, ComboBox<String> infomation_gender, DatePicker infomation_birthDay);
//2
}
