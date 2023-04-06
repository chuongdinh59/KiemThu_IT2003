/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.repository;

import com.bros.quanlythuvien.entity.ReaderEntity;
import java.util.List;
import java.util.Map;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 *
 * @author phu nguyen
 */
public interface ReaderRepository extends CommonRepository<ReaderEntity> {

    public ReaderEntity findReaderById(Integer ReaderID);

    public List<ReaderEntity> findAll(Integer page);

    public void loadCate(ComboBox<String> RsearchBook_category, Map<Integer, String> categoriesMap);

    public int checkReader(Integer id);

    public Map<String, Object> login(TextField username, TextField password, Button loginBtn);
    
     public int register(TextField register_username, TextField register_password, TextField register_fullname, TextField register_email);
}
