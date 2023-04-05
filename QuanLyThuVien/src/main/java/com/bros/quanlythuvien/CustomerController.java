/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien;

import com.bros.quanlythuvien.model.BookModel;
import com.bros.quanlythuvien.model.CategoryModel;
import com.bros.quanlythuvien.model.LoanSlipModel;
import com.bros.quanlythuvien.model.ReaderModel;
import com.bros.quanlythuvien.model.SearchBookModel;
import com.bros.quanlythuvien.service.BookService;
import com.bros.quanlythuvien.service.CategoryService;
import com.bros.quanlythuvien.service.EmployeeService;
import com.bros.quanlythuvien.service.ReaderService;
import com.bros.quanlythuvien.service.impl.BookServiceImpl;
import com.bros.quanlythuvien.service.impl.CategoryServiceImpl;
import com.bros.quanlythuvien.service.impl.EmployeeServiceImpl;
import com.bros.quanlythuvien.service.impl.ReaderServiceImpl;
import static com.bros.quanlythuvien.utils.ConnectionUtils.getConnection;
import com.bros.quanlythuvien.utils.ValidateUtils;
import java.io.IOException;
import java.net.URL;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionModel;
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
    private ComboBox<String> RsearchBook_category;

    @FXML
    private TextField RsearchBook_name;

    @FXML
    private TextField RsearchBook_publish;

    @FXML
    private TableView<BookModel> TBRSearchBook;

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
    private Button Cart_Btn;

    @FXML
    private Button submitCart_Btn;

    @FXML
    private TableView<BookModel> tb_Cart;

    @FXML
    private AnchorPane cart_viewForm;

    @FXML
    private Button clear_Btn;

    @FXML
    private TableView<ReaderModel> infoCustomerTB;

    @FXML
    private TableView<LoanSlipModel> infoLoanSlipTB;

    @FXML
    public void minimize() {
        Stage stage = (Stage) mainForm.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    public void close() {
        System.exit(0);
    }
    private BookService bookService;
    private ReaderService readerService = new ReaderServiceImpl();
    private EmployeeService employeeService = new EmployeeServiceImpl();

    private int LScheckReader = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bookService = new BookServiceImpl();
        setReaderId(readerId);
        loadRSearchBookColumn(TBRSearchBook, bookListCart);
        // Hàm này lỗi chỗ converter --> fix khỏi đóng conn hơi kì :v
        loadRSearchBookInfo(null, null);
        loadSearchCategory();
        loadCartColumn(tb_Cart, bookListCart);
//        readerId = (int) this.root.getUserData();
        loadReaderColumn(infoCustomerTB);
        loadLoanslipColumn(infoLoanSlipTB);

    }

    private Map<Integer, String> categoriesMap = new HashMap<>();
    List<BookModel> bookListCart = new ArrayList<>();

    private Integer readerId; // Thuộc tính readerId

    @FXML
    public void setReaderId(Integer id) {
        this.readerId = id;
    }

    @FXML
    public void checkReader() {
        int check = readerService.checkReader(readerId);
        if (check == 0) {
            LScheckReader = 0;
        } else {
            LScheckReader = 1;
        }

    }

    private int totalQuantity = 0;

    @FXML
    public void createOnlineBook() {
        if (LScheckReader == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Vui lòng nhấn nút kiểm tra trước");
            alert.showAndWait();
        } else {
            for (BookModel book : bookListCart) {
                totalQuantity += book.getQuantity();
            }
            if (totalQuantity <= 5) {
                String strReaderId = Integer.toString(readerId);
                readerService.creatLoanSlip(bookListCart, LScheckReader, strReaderId, 0);
                clearCart();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Không thể mượn quá 5 cuốn sách");
                alert.showAndWait();
            }
        }
        totalQuantity = 0;
    }

    @FXML
    private void loadReaderColumn(TableView<ReaderModel> infoCustomerTB) {
        readerService.loadReaderColumn(infoCustomerTB);
    }

    @FXML
    private void loadReaderInfo() {
        ReaderModel reader = readerService.findById(readerId);
        List<ReaderModel> readerList = new ArrayList<>();
        readerList.add(reader);
        this.infoCustomerTB.setItems(FXCollections.observableList(readerList));
    }

    @FXML
    private void loadLoanslipColumn(TableView<LoanSlipModel> infoLoanSlipTB) {
        employeeService.loadLoanslipColumn(infoLoanSlipTB);
    }

    @FXML
    private void loadLoanSlipInfo() {
//        List<LoanSlipModel> loanSlipList = new Array<>();
        List<LoanSlipModel> loanSlipList = new ArrayList<>();
        loanSlipList = readerService.findByCId(readerId);
        this.infoLoanSlipTB.setItems(FXCollections.observableList(loanSlipList));
    }

    @FXML
    private void clearCart() {
        bookListCart.clear();
        tb_Cart.refresh();
    }

    @FXML
    private void loadCartColumn(TableView<BookModel> tb_Cart, List<BookModel> bookListCart) {
        readerService.loadCartColumn(tb_Cart, bookListCart);
    }

    @FXML
    public void loadInfoCart(List<BookModel> bookListCart, TableView<BookModel> tb_Cart, Integer page) {
        readerService.loadInfoCart(bookListCart, tb_Cart, page);
    }

    @FXML
    private void loadSearchCategory() {
        readerService.loadCate(RsearchBook_category, categoriesMap);
    }

    @FXML
    private void loadRSearchBookColumn(TableView<BookModel> TBRSearchBook, List<BookModel> bookListCart
    ) {
        readerService.loadSearchBookColumn(TBRSearchBook, bookListCart);
    }

    @FXML
    private void loadRSearchBookInfo(Map<String, Object> searchMap, Integer page
    ) {
        List<BookModel> searchBookList = bookService.findBooks(searchMap, page);
        this.TBRSearchBook.setItems(FXCollections.observableList(searchBookList));
    }

    @FXML
    private void loadRSearch() {
        String strTitle = RsearchBook_name.getText();
        String strAuthor = RsearchBook_author.getText();
        String selectedCategory = RsearchBook_category.getValue();
        Integer cateID = null;
        for (Map.Entry<Integer, String> entry : categoriesMap.entrySet()) {
            if (entry.getValue().equals(selectedCategory)) {
                cateID = entry.getKey();
                break;
            }
        }
        String strPublish = RsearchBook_publish.getText();
        Map<String, Object> searchMap = bookService.getSearchMap(strTitle, strAuthor, cateID, strPublish);
        loadRSearchBookInfo(searchMap, null);
    }

    @FXML
    public void switchForm(ActionEvent event
    ) {
        if (event.getSource() == information_Btn) {
            loadReaderInfo();
            loadLoanSlipInfo();
            information_viewForm.setVisible(true);
            searchBook_viewForm.setVisible(false);
            cart_viewForm.setVisible(false);

        }
        if (event.getSource() == searchBook_Btn) {
            information_viewForm.setVisible(false);
            searchBook_viewForm.setVisible(true);
            cart_viewForm.setVisible(false);

        }
        if (event.getSource() == Cart_Btn) {
            cart_viewForm.setVisible(true);
            information_viewForm.setVisible(false);
            searchBook_viewForm.setVisible(false);
            loadInfoCart(bookListCart, tb_Cart, null);

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

}
