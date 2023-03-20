/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien;

import com.bros.quanlythuvien.model.ReaderModel;
import com.bros.quanlythuvien.service.ReaderService;
import com.bros.quanlythuvien.service.impl.ReaderServiceImpl;
import java.io.Reader;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private TableView<ReaderModel> tbReader;

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
        TableColumn colId = new TableColumn("ID");
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn colFName = new TableColumn("FullName");
        colFName.setCellValueFactory(new PropertyValueFactory("fullname"));
        TableColumn colGender = new TableColumn("Gender");
        colGender.setCellValueFactory(new PropertyValueFactory("gender"));
        TableColumn colBirth = new TableColumn("DateOfBirth");
        colBirth.setCellValueFactory(new PropertyValueFactory("dateOfBirth"));
        TableColumn colRType = new TableColumn("ReaderType");
        colRType.setCellValueFactory(new PropertyValueFactory("readerType"));

        this.tbReader.getColumns().addAll(colId, colFName, colGender, colBirth, colRType);
    }

    @FXML
    private void loadReaderInfo(Integer id) {
        ReaderService readerService = new ReaderServiceImpl();
        List<ReaderModel> readerList = readerService.findReaderById(id, null);
        for (ReaderModel reader : readerList) {
            System.out.println("Reader ID: " + reader.getId());
            System.out.println("Reader Name: " + reader.getFullname());
            System.out.println("Reader Gender: " + reader.getGender());
            System.out.println("Reader Date of Birth: " + reader.getDateOfBirth());
            System.out.println("Reader Type: " + reader.getReaderType());
            System.out.println("=======================");
        }
        System.out.println(" o day ne" + readerList);
        this.tbReader.setItems(FXCollections.observableList(readerList));
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
        loadReaderInfo(null);
    }
}
