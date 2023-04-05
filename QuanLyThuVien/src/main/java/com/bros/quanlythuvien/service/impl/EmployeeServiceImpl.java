/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.service.impl;

import com.bros.quanlythuvien.model.BookModel;
import com.bros.quanlythuvien.model.BorrowCardModel;
import com.bros.quanlythuvien.model.ReaderModel;
import com.bros.quanlythuvien.service.BorrowCardService;
import com.bros.quanlythuvien.service.EmployeeService;
import com.bros.quanlythuvien.service.ReaderService;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author phu nguyen
 */
public class EmployeeServiceImpl implements EmployeeService {

    private BorrowCardService borrowCardService = new BorrowCardServiceImpl();
    private ReaderService readerService = new ReaderServiceImpl();

    @Override
    public void loadBookColumn(TableView<BookModel> tb_SearchBook) {
        TableColumn colId = new TableColumn("BookID");
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn colName = new TableColumn("Title");
        colName.setCellValueFactory(new PropertyValueFactory("title"));
        TableColumn colAuthor = new TableColumn("Author");
        colAuthor.setCellValueFactory(new PropertyValueFactory("author"));
        TableColumn colDescription = new TableColumn("Description");
        colDescription.setCellValueFactory(new PropertyValueFactory("description"));
        TableColumn colPublishedYear = new TableColumn("Published Year");
        colPublishedYear.setCellValueFactory(new PropertyValueFactory("publicationYear"));
        TableColumn colPublishedPlace = new TableColumn("Published Place");
        colPublishedPlace.setCellValueFactory(new PropertyValueFactory("publicationPlace"));
        TableColumn colCategory = new TableColumn("Category");
        colCategory.setCellValueFactory(new PropertyValueFactory("categoryValue"));
        TableColumn colLocation = new TableColumn("Location");
        colLocation.setCellValueFactory(new PropertyValueFactory("location"));
        TableColumn colquantity = new TableColumn("Quantity");
        colquantity.setCellValueFactory(new PropertyValueFactory("quantity"));

        tb_SearchBook.getColumns().addAll(colId, colName, colAuthor, colDescription, colPublishedYear, colPublishedPlace, colCategory, colLocation, colquantity);
    }

    @Override
    public void loadReaderColumn(TableView<BorrowCardModel> tbReader) {
        TableColumn colRId = new TableColumn("ReaderId");
        colRId.setCellValueFactory(new PropertyValueFactory("readerID"));
        TableColumn colId = new TableColumn("BorrowCardID");
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn colFName = new TableColumn("FullName");
        colFName.setCellValueFactory(new PropertyValueFactory("fullName"));
        TableColumn colIssuedDate = new TableColumn("IssuedDate");
        colIssuedDate.setCellValueFactory(new PropertyValueFactory("issuedDate"));
        TableColumn colExpiredDate = new TableColumn("ExpiredDate");
        colExpiredDate.setCellValueFactory(new PropertyValueFactory("expiredDate"));

        tbReader.getColumns().addAll(colId, colRId, colFName, colIssuedDate, colExpiredDate);
    }

    @Override
    public void loadReaderInfo(Integer id, TableView<BorrowCardModel> tbReader) {
        List<BorrowCardModel> readerBorrowCardList = new ArrayList<>();
        if (id != null) {
            BorrowCardModel borrowCard = borrowCardService.findBorrowCardByRID(id);
            ReaderModel reader = readerService.findById(borrowCard.getReaderID());
            borrowCard.setFullName(reader.getFullname());
            readerBorrowCardList.add(borrowCard);
        } else {
            List<BorrowCardModel> borrowCardModels = borrowCardService.findAll();
            for (BorrowCardModel b : borrowCardModels) {
                readerBorrowCardList.add(b);
            }
        }
        tbReader.setItems(FXCollections.observableList(readerBorrowCardList));
    }

}
