/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.utils;

import com.bros.quanlythuvien.model.BookModel;
import com.bros.quanlythuvien.model.ReaderModel;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author ADMIN
 */
public class ReaderUtils {

    public static void load_info_cart(List<BookModel> bookListCart, TableView<BookModel> tb_Cart, Integer page) {
        tb_Cart.setItems(FXCollections.observableList(bookListCart));
        tb_Cart.refresh();
    }

    public static void load_cate(ComboBox<String> RsearchBook_category, Map<Integer, String> categoriesMap) {
        RsearchBook_category.setPromptText("Chọn thể loại");
        RsearchBook_category.getItems().add(0, "Chọn thể loại");
        RsearchBook_category.getItems().addAll(categoriesMap.values());

    }

    public static void load_reader_columns(TableView<ReaderModel> infoCustomerTB) {
        TableColumn colId = new TableColumn("ReaderId");
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn colName = new TableColumn("Fullname");
        colName.setCellValueFactory(new PropertyValueFactory("fullname"));
        TableColumn colGender = new TableColumn("Gender");
        colGender.setCellValueFactory(new PropertyValueFactory("gender"));
        TableColumn colDateOfBirth = new TableColumn("BirthDay");
        colDateOfBirth.setCellValueFactory(new PropertyValueFactory("dateOfBirth"));
        TableColumn colPhone = new TableColumn("Phone");
        colPhone.setCellValueFactory(new PropertyValueFactory("phone"));

        infoCustomerTB.getColumns().addAll(colId, colName, colGender, colDateOfBirth,colPhone);
    }

    public static void load_gender(ComboBox<String> infomation_gender) {
        infomation_gender.setPromptText("Chọn giới tính");
        infomation_gender.getItems().addAll("Chọn giới tính", "Nam", "Nữ");
    }

    public static ReaderModel create_readerModel(Integer readerId, TextField infomation_name, ComboBox<String> infomation_gender, DatePicker infomation_birthDay, TextField infomation_phone) {
        ReaderModel reader = new ReaderModel();
        reader.setId(readerId);
        reader.setFullname(infomation_name.getText());
        String gender = infomation_gender.getValue();
        if ("Nam".equals(gender) || "Nữ".equals(gender)) {
            reader.setGender(gender);
        } else {
            reader.setGender("");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedBirthDay = infomation_birthDay.getValue().format(formatter);
        reader.setDateOfBirth(formattedBirthDay);
        reader.setPhone(infomation_phone.getText());
        return reader;
    }
}
