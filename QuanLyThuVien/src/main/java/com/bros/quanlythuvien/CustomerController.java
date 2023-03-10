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
    private AnchorPane availableBooks_form;

    @FXML
    private Button availableBooks_importBtn;

    @FXML
    private TextField infomation_address;

    @FXML
    private DatePicker infomation_birthDay;

    @FXML
    private TableColumn<?, ?> infomation_col_address;

    @FXML
    private TableColumn<?, ?> infomation_col_birthDay;

    @FXML
    private TableColumn<?, ?> infomation_col_customerID;

    @FXML
    private TableColumn<?, ?> infomation_col_email;

    @FXML
    private TableColumn<?, ?> infomation_col_gender;

    @FXML
    private TableColumn<?, ?> infomation_col_name;

    @FXML
    private TableColumn<?, ?> infomation_col_number;

    @FXML
    private TextField infomation_email;

    @FXML
    private TextField infomation_gender;

    @FXML
    private ImageView infomation_importView;

    @FXML
    private TextField infomation_name;

    @FXML
    private TextField infomation_number;

    @FXML
    private TextField infomation_position;

    @FXML
    private Button infomation_updateBtn;

    @FXML
    private Button information_Btn;

    @FXML
    private AnchorPane information_viewForm;

    @FXML
    private Button logOut;

    @FXML
    private AnchorPane mainForm;

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
    private Label username;

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
        if (event.getSource() == information_Btn) {
            information_viewForm.setVisible(true);
            searchBook_viewForm.setVisible(false);
        }
        if (event.getSource() == searchBook_Btn) {
            information_viewForm.setVisible(false);
            searchBook_viewForm.setVisible(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
