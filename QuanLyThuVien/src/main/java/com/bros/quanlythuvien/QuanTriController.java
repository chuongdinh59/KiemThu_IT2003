package com.bros.quanlythuvien;

import java.io.IOException;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class QuanTriController {

    @FXML
    private void switchToDocGia() throws IOException {
        App.setRoot("DocGiaUI");
    }


    @FXML
    private TextField TxtFSearch;
    @FXML
    private Label lbName;
    @FXML
    private Label lbDayofReceipt;
    @FXML
    private Label lbPublished;
    @FXML
    private Label lbType;
    @FXML
    private Label lbPrice;
    @FXML
    private Button QuanTriButton;
 
}
