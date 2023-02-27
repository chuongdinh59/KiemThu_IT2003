package com.bros.quanlythuvien;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DocGiaController {

    @FXML
     private void switchToQuanTri() throws IOException {
        App.setRoot("QuanTriUI");
    }
     @FXML
    private Label lbMaDG;
    @FXML
    private Label lbDoiTuong;
    @FXML
    private Label lbNgaySinh;
    @FXML
    private Label lbGioiTinh;
    @FXML
    private Label lbBoPhan;
    @FXML
    private Label lbHanThe;
    @FXML
    private Label lbHoTen;
    @FXML
    private TextField txtFDiaChi;
    @FXML
    private TextField txtFEmail;
    @FXML
    private TextField txtFDienThoai;
    @FXML
    private Button DocGiaButton;
}
