/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien;

import com.bros.quanlythuvien.model.BorrowCardModel;
import com.bros.quanlythuvien.model.ReaderBorrowCardModel;
import com.bros.quanlythuvien.model.ReaderModel;
import com.bros.quanlythuvien.service.BorrowCardService;
import com.bros.quanlythuvien.service.ReaderService;
import com.bros.quanlythuvien.service.impl.BorrowCardServiceImpl;
import com.bros.quanlythuvien.service.impl.ReaderServiceImpl;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author ADMIN
 */
public class EmployeeController implements Initializable {

    @FXML
    private Button borrowBook_Btn;

    @FXML
    private Button createLoanslipBtn;

    @FXML
    private AnchorPane borrowBook_viewForm;

    @FXML
    private AnchorPane borrow_bookingStatusView;

    @FXML
    private AnchorPane loanslip_viewForm;

    @FXML
    private Button logOut;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private Button returnBook_Btn;

    @FXML
    private AnchorPane returnBook_viewForm;

    @FXML
    private Button searchBook_Btn;

    @FXML
    private TextField searchBook_author;

    @FXML
    private TextField searchBook_category;

    @FXML
    private TableColumn<?, ?> searchBook_col_author;

    @FXML
    private TableColumn<?, ?> searchBook_col_bookID;

    @FXML
    private TableColumn<?, ?> searchBook_col_category;

    @FXML
    private TableColumn<?, ?> searchBook_col_description;

    @FXML
    private TableColumn<?, ?> searchBook_col_location;

    @FXML
    private TableColumn<?, ?> searchBook_col_location1;

    @FXML
    private TableColumn<?, ?> searchBook_col_publishedDate;

    @FXML
    private TableColumn<?, ?> searchBook_col_publishedPlace;

    @FXML
    private TextField searchBook_name;

    @FXML
    private DatePicker searchBook_publishedDate;

    @FXML
    private AnchorPane searchBook_viewForm;

    @FXML
    private Button status_Btn;

    @FXML
    private AnchorPane status_viewForm;

    @FXML
    private Label username;

    @FXML
    private Button loanslip_exitBtn;

    @FXML
    private TableView<ReaderBorrowCardModel> tbReader;

    @FXML
    private Button findAllReaderBtn;

    @FXML
    private Button SearchReaderBtn;

    @FXML
    private TextField TFReaderId;

    @FXML
    public void minimize() {
        Stage stage = (Stage) mainForm.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    public void close() {
        System.exit(0);
    }

    @FXML
    private void loadReaderColumn() {
        TableColumn colId = new TableColumn("BorrowCardID");
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn colFName = new TableColumn("FullName");
        colFName.setCellValueFactory(new PropertyValueFactory("fullname"));
        TableColumn colRId = new TableColumn("ReaderId");
        colRId.setCellValueFactory(new PropertyValueFactory("readerID"));
        TableColumn colIssuedDate = new TableColumn("IssuedDate");
        colIssuedDate.setCellValueFactory(new PropertyValueFactory("issuedDate"));
        TableColumn colExpiredDate = new TableColumn("ExpiredDate");
        colExpiredDate.setCellValueFactory(new PropertyValueFactory("expiredDate"));

        this.tbReader.getColumns().addAll(colId, colFName, colRId, colIssuedDate, colExpiredDate);
    }

    @FXML
    private void loadReaderInfo(Integer id) {
        if (id != null) {
            ReaderService readerService = new ReaderServiceImpl();
            ReaderModel reader = readerService.findReaderById(id);
            BorrowCardService borrowCardService = new BorrowCardServiceImpl();
            BorrowCardModel borrowCard = borrowCardService.findBorrowCardByRID(id);

            List<ReaderBorrowCardModel> readerBorrowCardList = new ArrayList<>();
            ReaderBorrowCardModel readerBorrowCard = new ReaderBorrowCardModel(
                    borrowCard.getId(),
                    reader.getFullname(),
                    borrowCard.getReaderID(),
                    borrowCard.getIssuedDate(),
                    borrowCard.getExpiredDate()
            );
            readerBorrowCardList.add(readerBorrowCard);

            this.tbReader.setItems(FXCollections.observableList(readerBorrowCardList));
        } else if (id == null) {
            ReaderService readerService = new ReaderServiceImpl();
            List<ReaderModel> readerList = readerService.findAll();
            BorrowCardService borrowCardService = new BorrowCardServiceImpl();
            List<BorrowCardModel> borrowCardList = borrowCardService.findAll();

            List<ReaderBorrowCardModel> readerBorrowCardList = new ArrayList<>();
            for (ReaderModel reader : readerList) {
                for (BorrowCardModel borrowCard : borrowCardList) {
                    if (!Objects.equals(borrowCard.getId(), reader.getId())) {
                        continue;
                    }
                    ReaderBorrowCardModel readerBorrowCard = new ReaderBorrowCardModel(
                            borrowCard.getId(),
                            reader.getFullname(),
                            borrowCard.getReaderID(),
                            borrowCard.getIssuedDate(),
                            borrowCard.getExpiredDate()
                    );
                    readerBorrowCardList.add(readerBorrowCard);
                }
            }

            this.tbReader.setItems(FXCollections.observableList(readerBorrowCardList));
        }
    }

    @FXML
    public void loadAllReader() {
        loadReaderInfo(null);
    }

    @FXML
    public void loadReaderId() {
        String strId = TFReaderId.getText();
        System.out.println("chuoi o day ne : " + strId);
        if (!"".equals(strId)) {
            try {
                Integer id = Integer.valueOf(strId);
                BorrowCardService borrowCardService = new BorrowCardServiceImpl();
                BorrowCardModel borrowCard = borrowCardService.findBorrowCardByRID(id);
                System.out.println("borrowCard ơ day ne" + borrowCard);
                if (borrowCard != null) {
                    System.out.println("dang khong bang null ");
                    loadReaderInfo(id);
                } else if (borrowCard == null) {
                    System.out.println("dang bang null ne");
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Lỗi");
                    alert.setHeaderText("Error");
                    alert.setContentText("Không tìm thấy khách hàng");
                    alert.showAndWait();
                }

            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Lỗi");
                alert.setHeaderText("Error");
                alert.setContentText("Bạn cần phải nhập số");
                alert.showAndWait();
            }
        } else if ("".equals(strId)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Lỗi");
            alert.setHeaderText("Error");
            alert.setContentText("Bạn chưa nhập id");
            alert.showAndWait();
        }

    }

    @FXML
    public void switchForm(ActionEvent event) {
        if (event.getSource() == borrowBook_Btn) {
            borrowBook_viewForm.setVisible(true);
            searchBook_viewForm.setVisible(false);
            status_viewForm.setVisible(false);
            loanslip_viewForm.setVisible(false);
            returnBook_viewForm.setVisible(false);
        }
        if (event.getSource() == searchBook_Btn) {
            borrowBook_viewForm.setVisible(false);
            searchBook_viewForm.setVisible(true);
            status_viewForm.setVisible(false);
            loanslip_viewForm.setVisible(false);
            returnBook_viewForm.setVisible(false);
        }
        if (event.getSource() == status_Btn) {
            borrowBook_viewForm.setVisible(false);
            searchBook_viewForm.setVisible(false);
            status_viewForm.setVisible(true);
            loanslip_viewForm.setVisible(false);
            returnBook_viewForm.setVisible(false);
        }
        if (event.getSource() == createLoanslipBtn) {
            borrowBook_viewForm.setVisible(false);
            searchBook_viewForm.setVisible(false);
            status_viewForm.setVisible(false);
            loanslip_viewForm.setVisible(true);
            returnBook_viewForm.setVisible(false);
        }
        if (event.getSource() == returnBook_Btn) {
            borrowBook_viewForm.setVisible(false);
            searchBook_viewForm.setVisible(false);
            status_viewForm.setVisible(false);
            loanslip_viewForm.setVisible(false);
            returnBook_viewForm.setVisible(true);
        }
        if (event.getSource() == loanslip_exitBtn) {
            borrowBook_viewForm.setVisible(false);
            searchBook_viewForm.setVisible(false);
            status_viewForm.setVisible(true);
            loanslip_viewForm.setVisible(false);
            returnBook_viewForm.setVisible(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadReaderColumn();
//        loadReaderInfo(1);
    }
}
