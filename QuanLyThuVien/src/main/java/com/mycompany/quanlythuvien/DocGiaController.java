<<<<<<<< HEAD:QuanLyThuVien/src/main/java/com/bros/quanlythuvien/DocGiaController.java
package com.bros.quanlythuvien;
========
package com.mycompany.quanlythuvien;
>>>>>>>> c00f4dcbc1c99412a3ee9aeba31649538989cf9d:QuanLyThuVien/src/main/java/com/mycompany/quanlythuvien/DocGiaController.java

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DocGiaController {

    @FXML
<<<<<<<< HEAD:QuanLyThuVien/src/main/java/com/bros/quanlythuvien/DocGiaController.java
    private void switchToQuanTri() throws IOException {
        App.setRoot("QuanTriUI");
========
    private void switchToAdmin() throws IOException {
        App.setRoot("AdminUI");
>>>>>>>> c00f4dcbc1c99412a3ee9aeba31649538989cf9d:QuanLyThuVien/src/main/java/com/mycompany/quanlythuvien/DocGiaController.java
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
