module com.bros.quanlythuvien {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;

    opens com.bros.quanlythuvien to javafx.fxml;
    exports com.bros.quanlythuvien;

}
