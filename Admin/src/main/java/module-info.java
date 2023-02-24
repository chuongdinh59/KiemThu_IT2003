module com.mycompany.admin {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.admin to javafx.fxml;
    exports com.mycompany.admin;
}
