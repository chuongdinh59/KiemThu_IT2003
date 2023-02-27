module com.mycompany.quanlythuvien {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.quanlythuvien to javafx.fxml;
    exports com.mycompany.quanlythuvien;
}
