module com.bros.quanlythuvien {
    requires java.sql;
    requires commons.beanutils;
    requires lombok;
    requires modelmapper;
    requires de.jensd.fx.glyphs.fontawesome;
    requires com.jfoenix;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    opens com.bros.quanlythuvien to javafx.fxml;
    exports com.bros.quanlythuvien;

}
