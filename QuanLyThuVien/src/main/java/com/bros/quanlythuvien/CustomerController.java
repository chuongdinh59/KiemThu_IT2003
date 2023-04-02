/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien;

import com.bros.quanlythuvien.model.BookModel;
import com.bros.quanlythuvien.model.CategoryModel;
import com.bros.quanlythuvien.model.SearchBookModel;
import com.bros.quanlythuvien.service.BookService;
import com.bros.quanlythuvien.service.CategoryService;
import com.bros.quanlythuvien.service.impl.BookServiceImpl;
import com.bros.quanlythuvien.service.impl.CategoryServiceImpl;
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
import javafx.scene.control.Alert;
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
public class CustomerController implements Initializable {

    @FXML
    private TextField RsearchBook_author;

    @FXML
    private TextField RsearchBook_category;

    @FXML
    private TextField RsearchBook_name;

    @FXML
    private TextField RsearchBook_publish;

    @FXML
    private TableView<SearchBookModel> TBRSearchBook;

    @FXML
    private AnchorPane availableBooks_form;

    @FXML
    private Button availableBooks_importBtn;

    @FXML
    private TextField infomation_address;

    @FXML
    private DatePicker infomation_birthDay;

    @FXML
    private TextField infomation_email;

    @FXML
    private TextField infomation_gender;

    @FXML
    private ImageView infomation_importView;

    @FXML
    private TextField infomation_name;

    @FXML
    private TextField infomation_number;

    @FXML
    private TextField infomation_position;

    @FXML
    private Button infomation_updateBtn;

    @FXML
    private Button information_Btn;

    @FXML
    private AnchorPane information_viewForm;

    @FXML
    private Button logOut;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private Button searchBook_Btn;

    @FXML
    private AnchorPane searchBook_viewForm;

    @FXML
    private Label username;

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
    private void loadRSearchBookColumn() {
        TableColumn colId = new TableColumn("BookID");
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn colName = new TableColumn("Title");
        colName.setCellValueFactory(new PropertyValueFactory("title"));
        TableColumn colAuthor = new TableColumn("Author");
        colAuthor.setCellValueFactory(new PropertyValueFactory("author"));
        TableColumn colDescription = new TableColumn("Description");
        colDescription.setCellValueFactory(new PropertyValueFactory("description"));
        TableColumn colPublishedYear = new TableColumn("Published Year");
        colPublishedYear.setCellValueFactory(new PropertyValueFactory("publicationYear"));
        TableColumn colPublishedPlace = new TableColumn("Published Place");
        colPublishedPlace.setCellValueFactory(new PropertyValueFactory("publicationPlace"));
        TableColumn colCategory = new TableColumn("Category");
        colCategory.setCellValueFactory(new PropertyValueFactory("cate"));
        TableColumn colLocation = new TableColumn("Location");
        colLocation.setCellValueFactory(new PropertyValueFactory("location"));

        this.TBRSearchBook.getColumns().addAll(colId, colName, colAuthor, colDescription, colPublishedYear, colPublishedPlace, colCategory, colLocation);
    }

    @FXML
    private void loadRSearchBookInfo(Map<String, Object> searchMap) {
        BookService bookService = new BookServiceImpl();
        List<BookModel> bookList = bookService.findBooks(searchMap, null);
        CategoryService cateService = new CategoryServiceImpl();
        List<CategoryModel> cateList = cateService.findAll();

        List<SearchBookModel> searchBookList = new ArrayList<>();
        for (BookModel book : bookList) {
            for (CategoryModel cate : cateList) {
                if (!Objects.equals(book.getCategoryID(), cate.getCategoryID())) {
                    continue;
                }
                SearchBookModel searchBook = new SearchBookModel(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getDescription(),
                        book.getPublicationYear(),
                        book.getPublicationPlace(),
                        cate.getValue(),
                        book.getLocation()
                );
                searchBookList.add(searchBook);
            }
        }

        this.TBRSearchBook.setItems(FXCollections.observableList(searchBookList));
    }
    private PreparedStatement statement;
    private ResultSet result;

    @FXML
    private void loadRSearch() {
        String strTitle = RsearchBook_name.getText();
        String strAuthor = RsearchBook_author.getText();
        String strCate = RsearchBook_category.getText();
        String strPublish = RsearchBook_publish.getText();
        try {
            Map<String, Object> g = new HashMap<>();
            if (!"".equals(strTitle)) {

                Connection connect = getConnection();
                try {
                    String sql = "SELECT * FROM books WHERE BookTitle LIKE CONCAT('%', _utf8mb4 ?, '%') COLLATE utf8mb4_unicode_ci;";
                    statement = connect.prepareStatement(sql);
                    statement.setString(1, strTitle);
                    result = statement.executeQuery();

                    if (result.next()) {
                        g.put("BookTitle", strTitle);
                    } else {
                        g.put("BookTitle", "1234567898765542537");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            if (!"".equals(strAuthor)) {

                Connection connect = getConnection();
                try {
                    String sql = "SELECT * FROM books WHERE Author LIKE CONCAT('%', _utf8mb4 ?, '%') COLLATE utf8mb4_unicode_ci;";
                    statement = connect.prepareStatement(sql);
                    statement.setString(1, strAuthor);
                    result = statement.executeQuery();

                    if (result.next()) {
                        g.put("Author", strAuthor);
                    } else {
                        g.put("Author", "1234567898765542537");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            if (!"".equals(strCate)) {
                Connection connect = getConnection();
                try {
                    String sql = "SELECT id FROM category WHERE value LIKE CONCAT('%', _utf8mb4 ?, '%') COLLATE utf8mb4_unicode_ci;";
                    statement = connect.prepareStatement(sql);
                    statement.setString(1, strCate);
                    result = statement.executeQuery();

                    if (result.next()) {
                        String Cid = result.getString("id");

                        Integer Cate = Integer.valueOf(Cid);
                        g.put("categoryID", Cate);
                    } else {
                        g.put("categoryID", "ádhbakdshbaksd");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (!"".equals(strPublish)) {

                Connection connect = getConnection();
                try {
                    String sql = "SELECT * FROM books WHERE PublicationYear = ?";
                    statement = connect.prepareStatement(sql);
                    statement.setString(1, strPublish);
                    result = statement.executeQuery();

                    if (result.next()) {
                        Integer Publish = Integer.valueOf(strPublish);
                        g.put("publicationYear", Publish);
                    } else {
                        g.put("publicationYear", "ádhbakdshbaksd");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

               
            }
            loadRSearchBookInfo(g);

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Lỗi");
            alert.setHeaderText("Error");
            alert.setContentText("Bạn cần phải nhập số");
            alert.showAndWait();
        }
    }

    @FXML
    public void switchForm(ActionEvent event) {
        if (event.getSource() == information_Btn) {
            information_viewForm.setVisible(true);
            searchBook_viewForm.setVisible(false);
        }
        if (event.getSource() == searchBook_Btn) {
            information_viewForm.setVisible(false);
            searchBook_viewForm.setVisible(true);
        }
    }
    
    @FXML
    public void logOut() throws IOException{
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
        loadRSearchBookColumn();
        loadRSearchBookInfo(null);
    }
}
