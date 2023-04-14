/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.service.impl;

import com.bros.quanlythuvien.converter.ReaderConverter;
import com.bros.quanlythuvien.entity.ReaderEntity;
import com.bros.quanlythuvien.model.ReaderModel;
import com.bros.quanlythuvien.repository.ReaderRepository;
import com.bros.quanlythuvien.repository.impl.ReaderRepositoryImpl;
import com.bros.quanlythuvien.service.ReaderService;
import com.bros.quanlythuvien.utils.MessageBoxUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.scene.control.Alert;

/**
 *
 * @author phu nguyen
 */
public class ReaderServiceImpl implements ReaderService {

    ReaderRepository readerRepository = new ReaderRepositoryImpl();
    ReaderConverter readerConverter = new ReaderConverter();

    @Override
    public ReaderModel findById(Integer id) {
        ReaderEntity readerEntity = readerRepository.findById(id);
        return readerConverter.entityToModel(readerEntity, ReaderModel.class);
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
        switch (readerRepository.checkReader(id)) {
            case 0: {
                MessageBoxUtils.AlertBox("ERROR", "Thẻ thư viện đã hết hạn", Alert.AlertType.ERROR);
                return 0;
            }
            case 1:
                List<ReaderModel> readerList = findAll();
                for (ReaderModel reader : readerList) {
                    if (Objects.equals(reader.getId(), id)) {
                        MessageBoxUtils.AlertBox("INFORMATION", "Người dùng hợp lệ", Alert.AlertType.INFORMATION);
                    }
                }
                return 1;
            case 2: {
                MessageBoxUtils.AlertBox("ERROR", "Người dùng chưa tạo thẻ thư viện hoặc không tồn tại", Alert.AlertType.ERROR);
                return 0;
            }
            case 3: {
                MessageBoxUtils.AlertBox("ERROR", "Người dùng chưa trả sách", Alert.AlertType.ERROR);
                return 0;
            }
            default:
                break;
        }
        return 0;
    }

    @Override
    public boolean updateReader(ReaderModel reader) {
        boolean rs = readerRepository.updateReader(reader);
        return rs;
    }

}
