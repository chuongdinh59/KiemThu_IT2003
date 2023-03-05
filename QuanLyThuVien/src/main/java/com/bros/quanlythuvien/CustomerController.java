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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author ADMIN
 */
public class CustomerController implements Initializable {

    @FXML
    private Button availableBooks_Btn;

    @FXML
    private TextField availableBooks_Price;

    @FXML
    private Button availableBooks_addBtn;

    @FXML
    private TextField availableBooks_author;

    @FXML
    private TextField availableBooks_bookID;

    @FXML
    private TextField availableBooks_bookTitle;

    @FXML
    private Button availableBooks_clearID;

    @FXML
    private TableColumn<?, ?> availableBooks_col_author;
    @FXML
    private TableColumn<?, ?> availableBooks_col_bookID;

    @FXML
    private TableColumn<?, ?> availableBooks_col_bookTitle;

    @FXML
    private TableColumn<?, ?> availableBooks_col_genre;

    @FXML
    private TableColumn<?, ?> availableBooks_col_price;

    @FXML
    private TableColumn<?, ?> availableBooks_col_publishedDate;

    @FXML
    private Button availableBooks_deleteID;

    @FXML
    private AnchorPane availableBooks_form;

    @FXML
    private TextField availableBooks_genre;

    @FXML
    private Button availableBooks_importBtn;

    @FXML
    private ImageView availableBooks_importView;

    @FXML
    private DatePicker availableBooks_publishedDate;

    @FXML
    private TextField availableBooks_search;

    @FXML
    private Button availableBooks_updateID;

    @FXML
    private Button dashBoard_Btn;

    @FXML
    private Button logOut;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private Button purchase_Btn;

    @FXML
    private Label username;

    @FXML
    private Button customer_btn;
    @FXML
    private AnchorPane availableBooks_viewForm;
    @FXML
    private AnchorPane customer_viewForm;

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
        if (event.getSource() == availableBooks_Btn) {
            availableBooks_viewForm.setVisible(true);
            customer_viewForm.setVisible(false);
        }
        if (event.getSource() == customer_btn) {
            availableBooks_viewForm.setVisible(false);
            customer_viewForm.setVisible(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
