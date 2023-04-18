/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.utils;

/**
 *
 * @author Dinh Chuong
 */
import javafx.application.Platform;
import javafx.scene.control.Alert;

public class MessageBoxUtils {

    public static Alert AlertBox(String title, String content, Alert.AlertType type) {
        Alert a = new Alert(type);
        a.setTitle(title);
        a.setContentText(content);
        a.showAndWait();
        return a;
    }
}
