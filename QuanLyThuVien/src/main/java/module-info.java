<<<<<<< HEAD
module com.bros.quanlythuvien {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;

    opens com.bros.quanlythuvien to javafx.fxml;
    exports com.bros.quanlythuvien;
=======
module com.mycompany.quanlythuvien {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.quanlythuvien to javafx.fxml;
    exports com.mycompany.quanlythuvien;
>>>>>>> c00f4dcbc1c99412a3ee9aeba31649538989cf9d
}
