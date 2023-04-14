/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.utils;

import com.bros.quanlythuvien.model.LoanSlipModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 *
 * @author ADMIN
 */
public class LoanSlipUtils {
      public static void load_loanslip_columns(TableView<LoanSlipModel> returnLoanslipTB) {
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

        TableColumn colOnline = new TableColumn("Status");
        colOnline.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<LoanSlipModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<LoanSlipModel, String> p) {
                String returnStatus = p.getValue().getIsOnline() == 1 ? "Đã lấy sách" : "Chưa lấy sách";
                return new SimpleStringProperty(returnStatus);
            }
        });

        returnLoanslipTB.getColumns().addAll(colId, colRId, colBId, colBName, colBAuthor, colBorrowedDate, colExpiredDate, colQuantity, colReturn, colOnline);
    }
}
