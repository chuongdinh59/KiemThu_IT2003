/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.service.impl;

import com.bros.quanlythuvien.converter.AccountConverter;
import com.bros.quanlythuvien.converter.ReaderConverter;
import com.bros.quanlythuvien.entity.AccountEntity;
import com.bros.quanlythuvien.entity.ReaderEntity;
import com.bros.quanlythuvien.model.AccountModel;
import com.bros.quanlythuvien.model.ReaderModel;
import com.bros.quanlythuvien.repository.ReaderRepository;
import com.bros.quanlythuvien.repository.impl.ReaderRepositoryImpl;
import com.bros.quanlythuvien.service.ReaderService;
import com.bros.quanlythuvien.utils.MessageBoxUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javafx.scene.control.Alert;

/**
 *
 * @author phu nguyen
 */
public class ReaderServiceImpl implements ReaderService {

    ReaderRepository readerRepository = new ReaderRepositoryImpl();
    ReaderConverter readerConverter = new ReaderConverter();
    AccountConverter accountConverter = new AccountConverter();

    @Override
    public ReaderModel findById(Integer id) {
        ReaderEntity readerEntity = readerRepository.findById(id);
        if (readerEntity == null) {
            return null;
        }
        return readerConverter.entityToModel(readerEntity, ReaderModel.class);
    }

    @Override
    public AccountModel findAccountByRId(Integer id) {
        AccountEntity accountEntity = readerRepository.findAccountByRId(id);
        if(accountEntity == null)return null;
        return accountConverter.entityToModel(accountEntity, AccountModel.class);
    }

    @Override
    public List<ReaderModel> findAll() {
        List<ReaderEntity> borrowCardList = readerRepository.findAll(null);
        List<ReaderModel> resultsBorrowCardModel = new ArrayList<>();
        for (ReaderEntity entity : borrowCardList) {
            resultsBorrowCardModel.add(readerConverter.entityToModel(entity, ReaderModel.class));
        }
        return resultsBorrowCardModel;
    }

    @Override
    public List<ReaderModel> findReaderNotHaveBorrowCard() {
        List<ReaderEntity> readerList = readerRepository.findReaderNotHaveBorrowCard();
        List<ReaderModel> resultsReaderModel = new ArrayList<>();
        for (ReaderEntity entity : readerList) {
            resultsReaderModel.add(readerConverter.entityToModel(entity, ReaderModel.class));
        }
        return resultsReaderModel;
    }

    @Override
    public int checkReader(Integer id) {
        return readerRepository.checkReader(id);
    }

    @Override
    public boolean updateReader(ReaderModel reader) {
        boolean rs = readerRepository.updateReader(reader);
        return rs;
    }

    public boolean updateRoleAccount(String role, Integer id) {
        boolean rs = readerRepository.updateRoleAccount(role, id);
        return rs;
    }

    @Override
    public Map<String, Object> login(String username, String password) {
        return readerRepository.login(username, password);
    }

    @Override
    public int register(String register_username, String register_password, String register_fullname, String register_email) {
        return readerRepository.register(register_username, register_password, register_fullname, register_email);
    }

}
