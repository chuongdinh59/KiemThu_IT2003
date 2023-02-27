package com.mycompany.quanlythuvien;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DocGiaController {

    @FXML
    private void switchToAdmin() throws IOException {
        App.setRoot("AdminUI");
    }
    @FXML
    private Label lbMaDG;
    @FXML
    private Label lbHoTen;
    @FXML
    private Label lbGioiTinh;
    @FXML
    private Label lbNgaySinh;
    @FXML
    private Label lbDoiTuong;
    @FXML
    private Label lbBoPhan;
    @FXML
    private Label lbHanThe;
    @FXML
    private TextField txtFEmail;
    @FXML
    private TextField txtFDiaChi;
    @FXML
    private TextField txtFDienThoai;
}
