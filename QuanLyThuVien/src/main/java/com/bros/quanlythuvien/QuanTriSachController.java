/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
public class QuanTriSachController implements Initializable {

    @FXML
    private Button availableBooks_Btn;

    @FXML
    private Button availableBooks_addBtn;

    @FXML
    private TextField availableBooks_author;

    @FXML
    private TextField availableBooks_bookID;

    @FXML
    private TextField availableBooks_category;

    @FXML
    private Button availableBooks_clearID;

    @FXML
    private TableColumn<?, ?> availableBooks_col_author;

    @FXML
    private TableColumn<?, ?> availableBooks_col_bookID;

    @FXML
    private TableColumn<?, ?> availableBooks_col_category;

    @FXML
    private TableColumn<?, ?> availableBooks_col_description;

    @FXML
    private TableColumn<?, ?> availableBooks_col_location;

    @FXML
    private TableColumn<?, ?> availableBooks_col_publishedDate;

    @FXML
    private TableColumn<?, ?> availableBooks_col_publishedPlace;

    @FXML
    private Button availableBooks_deleteID;

    @FXML
    private TextField availableBooks_description;

    @FXML
    private AnchorPane availableBooks_form;

    @FXML
    private Button availableBooks_importBtn;

    @FXML
    private ImageView availableBooks_importView;

    @FXML
    private TextField availableBooks_location;

    @FXML
    private DatePicker availableBooks_publishedDate;

    @FXML
    private TextField availableBooks_publishedPlace;

    @FXML
    private TextField availableBooks_search;

    @FXML
    private Button availableBooks_updateID;

    @FXML
    private AnchorPane availableBooks_viewForm;

    @FXML
    private Button customer_btn;

    @FXML
    private AnchorPane customer_viewForm;

    @FXML
    private Button logOut;

    @FXML
    private AnchorPane mainForm;

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
        if (event.getSource() == availableBooks_Btn) {
            availableBooks_viewForm.setVisible(true);
            customer_viewForm.setVisible(false);
        }
        if (event.getSource() == customer_btn) {
            availableBooks_viewForm.setVisible(false);
            customer_viewForm.setVisible(true);
        }
    }
    
    @FXML
    public void logOut() throws IOException{
     logOut.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("LoginUI.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Login");
                    stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
