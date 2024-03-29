module com.bros.quanlythuvien {
    requires java.sql;
    requires commons.beanutils;
    requires java.mail;
    requires cloudinary.core;
    requires cloudinary.http44;
    requires lombok;
    requires modelmapper;
    requires org.apache.poi.ooxml;
    requires org.apache.poi.poi;
    requires de.jensd.fx.glyphs.fontawesome;
    requires com.jfoenix;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires jbcrypt;
    opens com.bros.quanlythuvien to javafx.fxml;
    exports com.bros.quanlythuvien;
    exports com.bros.quanlythuvien.model;
    exports com.bros.quanlythuvien.entity;
    exports com.bros.quanlythuvien.service.impl;
    exports com.bros.quanlythuvien.service;

}