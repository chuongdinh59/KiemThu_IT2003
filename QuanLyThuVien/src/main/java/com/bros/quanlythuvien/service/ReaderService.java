/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.service;

import com.bros.quanlythuvien.model.AccountModel;
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

    public int checkReader(Integer id);

    public boolean updateReader(ReaderModel reader);

    public List<ReaderModel> findReaderNotHaveBorrowCard();

    public AccountModel findAccountByRId(Integer id);
    public boolean updateRoleAccount(String role,Integer id);

}
