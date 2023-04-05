/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.service.impl;

import com.bros.quanlythuvien.converter.LoanslipConverter;
import com.bros.quanlythuvien.entity.LoanSlipEntity;
import com.bros.quanlythuvien.model.BookModel;
import com.bros.quanlythuvien.model.BorrowCardModel;
import com.bros.quanlythuvien.model.LoanSlipModel;
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
import com.bros.quanlythuvien.repository.LoanslipRepository;
import com.bros.quanlythuvien.repository.ReaderRepository;
import com.bros.quanlythuvien.repository.impl.LoanslipRepositoryImpl;
import com.bros.quanlythuvien.repository.impl.ReaderRepositoryImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

/**
 *
 * @author phu nguyen
 */
public class EmployeeServiceImpl implements EmployeeService {

    private BorrowCardService borrowCardService = new BorrowCardServiceImpl();
    private ReaderService readerService = new ReaderServiceImpl();
    private LoanslipRepository loanslipRepository = new LoanslipRepositoryImpl();

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

    LoanslipConverter loanslipConverter = new LoanslipConverter();

    @Override
    public List<LoanSlipModel> loadLoanslipInfo() {
        loanslipRepository.findAll(null);

        List<LoanSlipEntity> loanslipList = loanslipRepository.findAll(null);
        List<LoanSlipModel> resultsLoanslipModel = new ArrayList<>();
        for (LoanSlipEntity entity : loanslipList) {
            resultsLoanslipModel.add(loanslipConverter.entityToModel(entity, LoanSlipModel.class));
        }
        return resultsLoanslipModel;
    }

    @Override
    public LoanSlipModel findById(Integer id) {
        LoanSlipEntity loanSlipEntity = loanslipRepository.findById(id);
        if (loanSlipEntity == null) {
            return null;
        }
        return loanslipConverter.entityToModel(loanSlipEntity, LoanSlipModel.class);
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
            boolean rs = loanslipRepository.updateBook(loanSlip);
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
        loanslipRepository.checkOnlineLoanSlip();
    }

    @Override
    public List<LoanSlipModel> findByCId(Integer id) {
        List<LoanSlipEntity> loanslipList = loanslipRepository.findByCId(id);

        List<LoanSlipModel> resultsLoanslipModel = new ArrayList<>();
        for (LoanSlipEntity entity : loanslipList) {
            resultsLoanslipModel.add(loanslipConverter.entityToModel(entity, LoanSlipModel.class));
        }
        return resultsLoanslipModel;
    }
    
    @Override
    public List<LoanSlipModel> findByBId(Integer id) {
        List<LoanSlipEntity> loanslipList = loanslipRepository.findByBId(id);

        List<LoanSlipModel> resultsLoanslipModel = new ArrayList<>();
        for (LoanSlipEntity entity : loanslipList) {
            resultsLoanslipModel.add(loanslipConverter.entityToModel(entity, LoanSlipModel.class));
        }
        return resultsLoanslipModel;
    }

//    public void loadLSBookListInfo(ArrayList<BookModel> LSbookList, TableView<BookModel> LSTBBookList) {
//        ObservableList<BookModel> bookObservableList = FXCollections.observableArrayList();
//        bookObservableList.addAll(LSbookList);
//        LSTBBookList.setItems(bookObservableList);
//    }
//
//    public void loadLSBookListColumn(ArrayList<BookModel> LSbookList, TableView<BookModel> LSTBBookList) {
//        TableColumn<BookModel, Integer> bookIdColumn = new TableColumn<>("Book ID");
//        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
//
//        TableColumn<BookModel, String> nameColumn = new TableColumn<>("Name");
//        nameColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
//
//        TableColumn<BookModel, String> authorColumn = new TableColumn<>("Author");
//        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
//
//        TableColumn<BookModel, Integer> quantityColumn = new TableColumn<>("Quantity");
//        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
//
//        TableColumn<BookModel, Boolean> deleteColumn = new TableColumn<>("Delete");
//        deleteColumn.setCellValueFactory(cellData -> new SimpleBooleanProperty(true));
//        deleteColumn.setCellFactory(column -> new EmployeeService.DeleteButtonTableCell <BookModel> (LSbookList));
//
//        LSTBBookList.getColumns().addAll(bookIdColumn, nameColumn, authorColumn, quantityColumn, deleteColumn);
//
//    }
//
//    public class DeleteButtonTableCell<S> extends TableCell<S, Boolean> {
//
//        private final Button deleteButton;
//        private final List<S> itemList;
//
//        public DeleteButtonTableCell(List<S> itemList, ArrayList<BookModel> LSbookList, TableView<BookModel> LSTBBookList) {
//            this.deleteButton = new Button("Delete");
//            this.itemList = itemList;
//
//            this.deleteButton.setOnAction(event -> {
//                System.out.println("nut da duoc click");
//                S currentItem = (S) getTableRow().getItem();
//                if (currentItem != null) {
//                    itemList.remove(currentItem);
//                    loadLSBookListInfo(LSbookList, LSTBBookList);
//                    LSTBBookList.refresh();
//                }
//            });
//        }
//
//        @Override
//        protected void updateItem(Boolean item, boolean empty) {
//            super.updateItem(item, empty);
//
//            if (empty) {
//                setGraphic(null);
//                setText(null);
//            } else {
//                setGraphic(deleteButton);
//                setText(null);
//            }
//        }
//    }
}
