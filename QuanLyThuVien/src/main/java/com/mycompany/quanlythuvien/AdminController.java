package com.mycompany.quanlythuvien;

import java.io.IOException;
import javafx.fxml.FXML;

public class AdminController {

    @FXML
    private void switchToDocGia() throws IOException {
        App.setRoot("DocGiaUI");
    }
}