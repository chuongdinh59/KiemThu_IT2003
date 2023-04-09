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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import com.bros.quanlythuvien.repository.LoanSlipRepository;

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

    @Override
    public void creatLoanSlip(List<BookModel> LSbookList, int LScheckReader, String LSCustomerID, int online) {
        loanSlipRepository.creatLoanSlip(LSbookList, LScheckReader, LSCustomerID, online);
    }

    @Override
    public void updateBookGive(LoanSlipModel loanSlip) {

        if (loanSlip.getIsOnline() == 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("ERROR");
            alert.setContentText("Sách đã được lấy!!");
            alert.showAndWait();
        } else {
            boolean rs = loanSlipRepository.updateBookGive(loanSlip);
            if (rs) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Success");
                alert.setContentText("Trao sách thành công");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("ERROR");
                alert.setContentText("Trao sách thất bại");
                alert.showAndWait();
            }
        }
    }

    @Override
    public void loadLoanslipColumn(TableView<LoanSlipModel> returnLoanslipTB) {
        TableColumn colId = new TableColumn("LoanSlipId");
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn colRId = new TableColumn("ReaderId");
        colRId.setCellValueFactory(new PropertyValueFactory("customerID"));
        TableColumn colBId = new TableColumn("BookId");
        colBId.setCellValueFactory(new PropertyValueFactory("bookID"));
        TableColumn colBName = new TableColumn("Book Name");
        colBName.setCellValueFactory(new PropertyValueFactory("bookName"));
        TableColumn colBAuthor = new TableColumn("Book Author");
        colBAuthor.setCellValueFactory(new PropertyValueFactory("bookAuthor"));
        TableColumn colBorrowedDate = new TableColumn("BorrowedDate");
        colBorrowedDate.setCellValueFactory(new PropertyValueFactory("borrowedDate"));
        TableColumn colExpiredDate = new TableColumn("ExpirationDate");
        colExpiredDate.setCellValueFactory(new PropertyValueFactory("expirationDate"));
        TableColumn colQuantity = new TableColumn("Quantity");
        colQuantity.setCellValueFactory(new PropertyValueFactory("quantity"));

        TableColumn colReturn = new TableColumn("Return");
        colReturn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<LoanSlipModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<LoanSlipModel, String> p) {
                String returnStatus = p.getValue().getIsReturned() == 1 ? "Đã trả" : "Chưa trả";
                return new SimpleStringProperty(returnStatus);
            }
        });

        TableColumn colOnline = new TableColumn("Online");
        colOnline.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<LoanSlipModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<LoanSlipModel, String> p) {
                String returnStatus = p.getValue().getIsOnline() == 1 ? "Đã lấy sách" : "Chưa lấy sách";
                return new SimpleStringProperty(returnStatus);
            }
        });

        returnLoanslipTB.getColumns().addAll(colId, colRId, colBId, colBName, colBAuthor, colBorrowedDate, colExpiredDate, colQuantity, colReturn, colOnline);
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
    public void updateBook(LoanSlipModel loanSlip) {

        if (loanSlip.getIsReturned() == 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("ERROR");
            alert.setContentText("Sách đã được trả!!");
            alert.showAndWait();
        } else {
            boolean rs = loanSlipRepository.updateBook(loanSlip);
            if (rs) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Success");
                alert.setContentText("Trả sách thành công");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("ERROR");
                alert.setContentText("Trả sách thất bại");
                alert.showAndWait();
            }
        }
    }

    @Override
    public void checkOnlineLoanSlip() {
        loanSlipRepository.checkOnlineLoanSlip();
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
