package com.bros.quanlythuvien;

import java.io.IOException;
import javafx.fxml.FXML;

public class QuanTriController {

    @FXML
    private void switchToDocGia() throws IOException {
        App.setRoot("DocGiaUI");
    }
}