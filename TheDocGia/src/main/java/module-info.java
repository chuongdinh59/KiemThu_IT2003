module com.mycompany.thedocgia {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.thedocgia to javafx.fxml;
    exports com.mycompany.thedocgia;
}
