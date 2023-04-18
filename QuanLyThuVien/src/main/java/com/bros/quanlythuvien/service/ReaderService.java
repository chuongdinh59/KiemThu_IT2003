/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.service;

import com.bros.quanlythuvien.model.AccountModel;
import com.bros.quanlythuvien.model.ReaderModel;
import java.util.List;
import java.util.Map;

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

    public Map<String, Object> login(String username, String password);

    public int register(String register_username, String register_password, String register_fullname, String register_email);

    public boolean updateRoleAccount(String role, Integer id);

}
