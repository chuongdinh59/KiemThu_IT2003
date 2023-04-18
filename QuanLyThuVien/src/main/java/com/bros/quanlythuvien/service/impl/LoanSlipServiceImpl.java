/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.service.impl;

import com.bros.quanlythuvien.converter.LoanslipConverter;
import com.bros.quanlythuvien.entity.LoanSlipEntity;
import com.bros.quanlythuvien.model.BookModel;
import com.bros.quanlythuvien.model.LoanSlipModel;
import com.bros.quanlythuvien.model.ReportModel;
import com.bros.quanlythuvien.repository.impl.LoanSlipRepositoryImpl;
import com.bros.quanlythuvien.service.LoanSlipService;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import com.bros.quanlythuvien.repository.LoanSlipRepository;
import com.bros.quanlythuvien.utils.MessageBoxUtils;

/**
 *
 * @author phu nguyen
 */
public class LoanSlipServiceImpl implements LoanSlipService {

    LoanSlipRepository loanSlipRepository = new LoanSlipRepositoryImpl();
    LoanslipConverter loanSlipConverter = new LoanslipConverter();

    @Override
    public List<LoanSlipModel> findByCId(Integer id) {
        List<LoanSlipEntity> loanslipList = loanSlipRepository.findByCId(id);
        List<LoanSlipModel> resultsLoanslipModel = new ArrayList<>();
        for (LoanSlipEntity entity : loanslipList) {
            resultsLoanslipModel.add(loanSlipConverter.entityToModel(entity, LoanSlipModel.class));
        }
        return resultsLoanslipModel;
    }

    public List<LoanSlipModel> findByBookIDAndReaderID(Integer bookID, Integer readerID) {
        List<LoanSlipModel> resutlt = new ArrayList<>();
        List<LoanSlipEntity> loanslipList = loanSlipRepository.findByBookIDAndReaderID(bookID, readerID);
        for (LoanSlipEntity entity : loanslipList) {
            resutlt.add(loanSlipConverter.entityToModel(entity, LoanSlipModel.class));
        }
        return resutlt;
    }

    @Override

    public Boolean creatLoanSlip(List<BookModel> LSbookList, int LScheckReader, String LSCustomerID, int online) {
        return loanSlipRepository.creatLoanSlip(LSbookList, LScheckReader, LSCustomerID, online);
    }

    @Override
    public Boolean updateBookGive(LoanSlipModel loanSlip) {

        if (loanSlip.getIsOnline() == 1) {
            MessageBoxUtils.AlertBox("ERROR", "Sách đã được lấy!!", Alert.AlertType.ERROR);
            return false;
        } else {
            boolean rs = loanSlipRepository.updateBookGive(loanSlip);
            if (rs) {
                MessageBoxUtils.AlertBox("INFORMATION", "Trao sách thành công", Alert.AlertType.INFORMATION);
            } else {
                MessageBoxUtils.AlertBox("ERROR", "Trao sách thất bại", Alert.AlertType.ERROR);
            }
            return rs;
        }
    }

    @Override
    public List<LoanSlipModel> loadLoanslipInfo() {
        loanSlipRepository.findAll(null);

        List<LoanSlipEntity> loanslipList = loanSlipRepository.findAll(null);
        List<LoanSlipModel> resultsLoanslipModel = new ArrayList<>();
        for (LoanSlipEntity entity : loanslipList) {
            resultsLoanslipModel.add(loanSlipConverter.entityToModel(entity, LoanSlipModel.class));
        }
        return resultsLoanslipModel;
    }

    @Override
    public LoanSlipModel findById(Integer id) {
        LoanSlipEntity loanSlipEntity = loanSlipRepository.findById(id);
        if (loanSlipEntity == null) {
            return null;
        }
        return loanSlipConverter.entityToModel(loanSlipEntity, LoanSlipModel.class);
    }

    @Override
    public Boolean updateBook(LoanSlipModel loanSlip) {

        if (loanSlip.getIsReturned() == 1) {
            MessageBoxUtils.AlertBox("ERROR", "Sách đã được trả!!", Alert.AlertType.ERROR);
            return false;
        } else {
            boolean rs = loanSlipRepository.updateBook(loanSlip);
            if (rs) {
                MessageBoxUtils.AlertBox("INFORMATION", "Trả sách thành công", Alert.AlertType.INFORMATION);
            } else {
                MessageBoxUtils.AlertBox("ERROR", "Trả sách thất bại", Alert.AlertType.ERROR);
            }
            return rs;
        }
    }

    // return 0 --> không có phần tử để xóa
    // return 1 --> xóa thành công
    // return -1 --> xóa thất bại
    @Override
    public Integer checkOnlineLoanSlip() {
        int rs = loanSlipRepository.checkOnlineLoanSlip();

        return rs;
    }

    @Override
    public List<LoanSlipModel> findByBId(Integer id) {
        List<LoanSlipEntity> loanslipList = loanSlipRepository.findByBId(id);

        List<LoanSlipModel> resultsLoanslipModel = new ArrayList<>();
        for (LoanSlipEntity entity : loanslipList) {
            resultsLoanslipModel.add(loanSlipConverter.entityToModel(entity, LoanSlipModel.class));
        }
        return resultsLoanslipModel;
    }

    @Override
    public List<ReportModel> getReportBorrow() {
        return loanSlipRepository.getReportBorrow();
    }

    @Override
    public List<ReportModel> getReportReturn() {
        return loanSlipRepository.getReportReturn();
    }

}
