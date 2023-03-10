/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
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
    public void minimize() {
        Stage stage = (Stage) mainForm.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    public void close() {
        System.exit(0);
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

    }
}
