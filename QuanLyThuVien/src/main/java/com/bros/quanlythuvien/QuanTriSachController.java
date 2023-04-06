/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien;

import com.bros.quanlythuvien.model.BookModel;
import com.bros.quanlythuvien.model.ReaderModel;
import com.bros.quanlythuvien.service.BookService;
import com.bros.quanlythuvien.service.CategoryService;
import com.bros.quanlythuvien.service.ReaderService;
import com.bros.quanlythuvien.service.impl.BookServiceImpl;
import com.bros.quanlythuvien.service.impl.CategoryServiceImpl;
import com.bros.quanlythuvien.service.impl.ReaderServiceImpl;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    private ComboBox<String> availableBooks_category;

    @FXML
    private Button availableBooks_clearID;

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
    private TextField availableBooks_publishedYear;

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
    private TextField availableBooks_title;

    @FXML
    private TableView<BookModel> tbBook;

    @FXML
    private TableView<ReaderModel> tbReader;

    @FXML
    private TextField availableBooks_quantity;

    @FXML
    private Button addBook_addBtn;

    @FXML
    private TextField addBook_author;

    @FXML
    private ComboBox<String> addBook_category;

    @FXML
    private TextField addBook_description;

    @FXML
    private Button addBook_exitBtn;

    @FXML
    private TextField addBook_location;

    @FXML
    private TextField addBook_publishedPlace;

    @FXML
    private TextField addBook_publishedYear;

    @FXML
    private TextField addBook_quantity;

    @FXML
    private TextField addBook_title;

    @FXML
    private AnchorPane add_viewForm;

    BookService bookService;
    CategoryService categoryService;
    private Map<Integer, String> categoriesMap = new HashMap<>();
    private ReaderService readerService = new ReaderServiceImpl();

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
            loadReaderInfo();
            availableBooks_viewForm.setVisible(false);
            customer_viewForm.setVisible(true);
        }
        if (event.getSource() == availableBooks_addBtn) {
            availableBooks_viewForm.setVisible(false);
            add_viewForm.setVisible(true);
        }
        if (event.getSource() == addBook_exitBtn) {
            availableBooks_viewForm.setVisible(true);
            add_viewForm.setVisible(false);
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
        this.bookService = new BookServiceImpl();
        this.categoryService = new CategoryServiceImpl();
        loadReaderColumn();
        loadBookColumn();
        loadBookInfo(null, null);
        loadCateAvailable();
        loadCateAdd();
        clear();
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
        colcate.setCellValueFactory(new PropertyValueFactory("categoryValue"));
        TableColumn colquantity = new TableColumn("Quantity");
        colquantity.setCellValueFactory(new PropertyValueFactory("quantity"));

        this.tbBook.getColumns().addAll(colId, colName, colAuthor, colPubDate, colcate, colquantity);
    }

    @FXML
    private void loadBookInfo(Map<String, Object> searchMap, Integer page) {
        List<BookModel> searchBookList = bookService.findBooks(searchMap, page);
        this.tbBook.setItems(FXCollections.observableList(searchBookList));
    }

    @FXML
    private void TBInfor() {
        BookModel rowData = tbBook.getSelectionModel().getSelectedItem();
        if (rowData != null) {
            availableBooks_bookID.setText(rowData.getId().toString());
            availableBooks_title.setText(rowData.getTitle());
            availableBooks_author.setText(rowData.getAuthor());
            availableBooks_description.setText(rowData.getDescription());
            availableBooks_publishedPlace.setText(rowData.getPublicationPlace());
            availableBooks_publishedYear.setText(rowData.getPublicationYear().toString());
            availableBooks_category.setValue(rowData.getCategoryValue());
            availableBooks_location.setText(rowData.getLocation());
            availableBooks_quantity.setText(rowData.getQuantity().toString());

        }
    }

    @FXML
    private void clear() {
        availableBooks_bookID.setText("");
        availableBooks_title.setText("");
        availableBooks_author.setText("");
        availableBooks_description.setText("");
        availableBooks_publishedPlace.setText("");
        availableBooks_publishedYear.setText("");
        availableBooks_category.setValue("Chọn thể loại");
        availableBooks_location.setText("");
        availableBooks_quantity.setText("");
    }

    //hiển thị cột trong bảng reader
    @FXML
    private void loadReaderColumn() {
        readerService.loadReaderColumn(tbReader);
    }

    //hiển thị dữ liệu bảng reader
    @FXML
    private void loadReaderInfo() {
        List<ReaderModel> readerList = readerService.findAll();
        this.tbReader.setItems(FXCollections.observableList(readerList));
    }

    @FXML
    private void loadCateAvailable() {
        categoryService.loadCate(availableBooks_category, categoriesMap);
    }

    @FXML
    private void loadCateAdd() {
        categoryService.loadCate(addBook_category, categoriesMap);
    }

    @FXML
    private void updateBook() {
        BookModel book = bookService.getBook(availableBooks_bookID, availableBooks_title,
                availableBooks_author, availableBooks_description, availableBooks_publishedPlace,
                availableBooks_publishedYear, availableBooks_category, availableBooks_location, availableBooks_quantity, categoriesMap);
        bookService.updateBook(book);
        loadBookInfo(null, null);
        clear();

    }

    @FXML
    private void insertBook() {

        availableBooks_bookID.setText("0");

        BookModel book = bookService.getBook(availableBooks_bookID, addBook_title,
                addBook_author, addBook_description, addBook_publishedPlace,
                addBook_publishedYear, addBook_category, addBook_location, addBook_quantity, categoriesMap);
        if (book != null) {
            bookService.inserBook(book);
        }
        loadBookInfo(null, null);
        clear();

    }

    @FXML
    private void deleteBook() {
        BookModel book = bookService.getBook(availableBooks_bookID, availableBooks_title,
                availableBooks_author, availableBooks_description, availableBooks_publishedPlace,
                availableBooks_publishedYear, availableBooks_category, availableBooks_location, availableBooks_quantity, categoriesMap);
        if (book != null) {
            bookService.deleteBook(book.getId());
        }
        loadBookInfo(null, null);
        clear();

    }

}
