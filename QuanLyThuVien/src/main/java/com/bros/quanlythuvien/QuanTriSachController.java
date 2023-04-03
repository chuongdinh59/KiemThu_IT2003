/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien;

import com.bros.quanlythuvien.model.BookModel;
import com.bros.quanlythuvien.model.BorrowCardModel;
import com.bros.quanlythuvien.model.CategoryModel;
import com.bros.quanlythuvien.model.ReaderBorrowCardModel;
import com.bros.quanlythuvien.model.ReaderModel;
import com.bros.quanlythuvien.model.SearchBookModel;
import com.bros.quanlythuvien.service.BookService;
import com.bros.quanlythuvien.service.BorrowCardService;
import com.bros.quanlythuvien.service.CategoryService;
import com.bros.quanlythuvien.service.ReaderService;
import com.bros.quanlythuvien.service.impl.BookServiceImpl;
import com.bros.quanlythuvien.service.impl.BorrowCardServiceImpl;
import com.bros.quanlythuvien.service.impl.CategoryServiceImpl;
import com.bros.quanlythuvien.service.impl.ReaderServiceImpl;
import static com.bros.quanlythuvien.utils.ConnectionUtils.getConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author ADMIN
 */
public class QuanTriSachController implements Initializable {

    @FXML
    private Button availableBooks_Btn;

    @FXML
    private Button availableBooks_addBtn;

    @FXML
    private TextField availableBooks_author;

    @FXML
    private TextField availableBooks_bookID;

    @FXML
    private TextField availableBooks_category;

    @FXML
    private Button availableBooks_clearID;

    @FXML
    private TableColumn<?, ?> availableBooks_col_author;

    @FXML
    private TableColumn<?, ?> availableBooks_col_bookID;

    @FXML
    private TableColumn<?, ?> availableBooks_col_category;

    @FXML
    private TableColumn<?, ?> availableBooks_col_description;

    @FXML
    private TableColumn<?, ?> availableBooks_col_location;

    @FXML
    private TableColumn<?, ?> availableBooks_col_publishedDate;

    @FXML
    private TableColumn<?, ?> availableBooks_col_publishedPlace;

    @FXML
    private Button availableBooks_deleteID;

    @FXML
    private TextField availableBooks_description;

    @FXML
    private AnchorPane availableBooks_form;

    @FXML
    private Button availableBooks_importBtn;

    @FXML
    private ImageView availableBooks_importView;

    @FXML
    private TextField availableBooks_location;

    @FXML
    private DatePicker availableBooks_publishedDate;

    @FXML
    private TextField availableBooks_publishedPlace;

    @FXML
    private TextField availableBooks_search;

    @FXML
    private Button availableBooks_updateID;

    @FXML
    private AnchorPane availableBooks_viewForm;

    @FXML
    private Button customer_btn;

    @FXML
    private AnchorPane customer_viewForm;

    @FXML
    private Button logOut;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private Label username;

    @FXML
    private TableView<BookModel> tbBook;
    
    BookService bookService;
    @FXML
    public void minimize() {
        Stage stage = (Stage) mainForm.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    public void close() {
        System.exit(0);
    }

    @FXML
    public void switchForm(ActionEvent event) {
        if (event.getSource() == availableBooks_Btn) {
            availableBooks_viewForm.setVisible(true);
            customer_viewForm.setVisible(false);
        }
        if (event.getSource() == customer_btn) {
            availableBooks_viewForm.setVisible(false);
            customer_viewForm.setVisible(true);
        }
    }

    @FXML
    public void logOut() throws IOException {
        logOut.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("LoginUI.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bookService = new BookServiceImpl();
        loadBookColumn();
        loadBookInfo(null, null);
    }

    @FXML
    private void loadBookColumn() {
        TableColumn colId = new TableColumn("BookID");
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn colName = new TableColumn("Title");
        colName.setCellValueFactory(new PropertyValueFactory("title"));
        TableColumn colAuthor = new TableColumn("Author");
        colAuthor.setCellValueFactory(new PropertyValueFactory("author"));
        TableColumn colPubDate = new TableColumn("Published Year");
        colPubDate.setCellValueFactory(new PropertyValueFactory("publicationYear"));
        TableColumn colcate = new TableColumn("Category");
        colcate.setCellValueFactory(new PropertyValueFactory("cate"));
        TableColumn colquantity = new TableColumn("Quantity");
        colquantity.setCellValueFactory(new PropertyValueFactory("quantity"));

        this.tbBook.getColumns().addAll(colId, colName, colAuthor, colPubDate, colcate, colquantity);
    }

    private void loadBookInfo(Map<String, Object> searchMap, Integer page) {
        List<BookModel> searchBookList = bookService.findBooks(searchMap, page);
        this.tbBook.setItems(FXCollections.observableList(searchBookList));
    }
}
