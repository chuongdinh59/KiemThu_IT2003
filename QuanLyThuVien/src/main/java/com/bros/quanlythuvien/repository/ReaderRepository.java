/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.repository;

import com.bros.quanlythuvien.entity.AccountEntity;
import com.bros.quanlythuvien.entity.ReaderEntity;
import com.bros.quanlythuvien.model.AccountModel;
import com.bros.quanlythuvien.model.ReaderModel;
import java.util.List;
import java.util.Map;

/**
 *
 * @author phu nguyen
 */
public interface ReaderRepository extends CommonRepository<ReaderEntity> {

    public ReaderEntity findReaderById(Integer ReaderID);

    public List<ReaderEntity> findAll(Integer page);

    public Map<Integer, String> loadCate(Map<Integer, String> categoriesMap);

    public int checkReader(Integer id);

    public Map<String, Object> login(String username, String password);

    public int register(String register_username, String register_password, String register_fullname, String register_email);

    public boolean updateReader(ReaderModel reader);

    public List<ReaderEntity> findReaderNotHaveBorrowCard();

    public List<Integer> findReaderId();
    
    public AccountEntity findAccountByRId(Integer id);
    public boolean updateRoleAccount(String role,Integer id);

}
