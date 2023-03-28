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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author ADMIN
 */
public class EmployeeController implements Initializable {

    @FXML
    private Button borrowBook_Btn;

    @FXML
    private Button createLoanslipBtn;

    @FXML
    private AnchorPane borrowBook_viewForm;

    @FXML
    private AnchorPane borrow_bookingStatusView;

    @FXML
    private AnchorPane loanslip_viewForm;

    @FXML
    private Button logOut;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private Button returnBook_Btn;

    @FXML
    private AnchorPane returnBook_viewForm;

    @FXML
    private Button searchBook_Btn;

    @FXML
    private TextField searchBook_author;

    @FXML
    private TextField searchBook_category;

    @FXML
    private TextField searchBook_name;

    @FXML
    private DatePicker searchBook_publishedDate;

    @FXML
    private AnchorPane searchBook_viewForm;

    @FXML
    private Button status_Btn;

    @FXML
    private AnchorPane status_viewForm;

    @FXML
    private Label username;

    @FXML
    private Button loanslip_exitBtn;

    @FXML
    private TableView<ReaderBorrowCardModel> tbReader;

    @FXML
    private Button findAllReaderBtn;

    @FXML
    private Button SearchReaderBtn;

    @FXML
    private TextField TFReaderId;

    @FXML
    private TableView<SearchBookModel> tbBook;

    @FXML
    private TextField TFBookId;

    @FXML
    private Button findAllBookBtn;

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
    private void loadReaderColumn() {
        TableColumn colId = new TableColumn("BorrowCardID");
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn colFName = new TableColumn("FullName");
        colFName.setCellValueFactory(new PropertyValueFactory("fullname"));
        TableColumn colRId = new TableColumn("ReaderId");
        colRId.setCellValueFactory(new PropertyValueFactory("readerID"));
        TableColumn colIssuedDate = new TableColumn("IssuedDate");
        colIssuedDate.setCellValueFactory(new PropertyValueFactory("issuedDate"));
        TableColumn colExpiredDate = new TableColumn("ExpiredDate");
        colExpiredDate.setCellValueFactory(new PropertyValueFactory("expiredDate"));

        this.tbReader.getColumns().addAll(colId, colFName, colRId, colIssuedDate, colExpiredDate);
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

    @FXML
    private void loadReaderInfo(Integer id) {
        if (id != null) {
            ReaderService readerService = new ReaderServiceImpl();
            ReaderModel reader = readerService.findReaderById(id);
            BorrowCardService borrowCardService = new BorrowCardServiceImpl();
            BorrowCardModel borrowCard = borrowCardService.findBorrowCardByRID(id);

            List<ReaderBorrowCardModel> readerBorrowCardList = new ArrayList<>();
            ReaderBorrowCardModel readerBorrowCard = new ReaderBorrowCardModel(
                    borrowCard.getId(),
                    reader.getFullname(),
                    borrowCard.getReaderID(),
                    borrowCard.getIssuedDate(),
                    borrowCard.getExpiredDate()
            );
            readerBorrowCardList.add(readerBorrowCard);

            this.tbReader.setItems(FXCollections.observableList(readerBorrowCardList));
        } else if (id == null) {
            ReaderService readerService = new ReaderServiceImpl();
            List<ReaderModel> readerList = readerService.findAll();
            BorrowCardService borrowCardService = new BorrowCardServiceImpl();
            List<BorrowCardModel> borrowCardList = borrowCardService.findAll();

            List<ReaderBorrowCardModel> readerBorrowCardList = new ArrayList<>();
            for (ReaderModel reader : readerList) {
                for (BorrowCardModel borrowCard : borrowCardList) {
                    if (!Objects.equals(borrowCard.getId(), reader.getId())) {
                        continue;
                    }
                    ReaderBorrowCardModel readerBorrowCard = new ReaderBorrowCardModel(
                            borrowCard.getId(),
                            reader.getFullname(),
                            borrowCard.getReaderID(),
                            borrowCard.getIssuedDate(),
                            borrowCard.getExpiredDate()
                    );
                    readerBorrowCardList.add(readerBorrowCard);
                }
            }

            this.tbReader.setItems(FXCollections.observableList(readerBorrowCardList));
        }
    }
    private PreparedStatement statement;
    private ResultSet result;

    @FXML
    private void loadBookInfo(Integer id) {
        int categoryID = 0;

        if (id != null) {
            BookService bookService = new BookServiceImpl();
            Map<String, Object> g = new HashMap<>();
            g.put("id", id);
            List<BookModel> bookList = bookService.findBooks(g, null);

            Connection connect = getConnection();
            try {
                String sql = "SELECT CategoryID FROM books WHERE id = ?";
                PreparedStatement statement = connect.prepareStatement(sql);
                statement.setInt(1, id);
                result = statement.executeQuery();

                if (result.next()) {
                    categoryID = result.getInt("CategoryID");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            CategoryService cateService = new CategoryServiceImpl();
            CategoryModel cate = cateService.findById(categoryID);

            List<SearchBookModel> searchBookList = new ArrayList<>();

            for (BookModel book : bookList) {
                if (!Objects.equals(book.getCategoryID(), cate.getCategoryID())) {
                    continue;
                }

                SearchBookModel searchBook = new SearchBookModel(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getPublicationYear(),
                        cate.getValue(),
                        book.getQuantity()
                );
                searchBookList.add(searchBook);
            }

            this.tbBook.setItems(FXCollections.observableList(searchBookList));
        } else if (id == null) {

            BookService bookService = new BookServiceImpl();
            List<BookModel> bookList = bookService.findBooks(null, null);
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
                            book.getPublicationYear(),
                            cate.getValue(),
                            book.getQuantity()
                    );
                    searchBookList.add(searchBook);
                }

            }

            this.tbBook.setItems(FXCollections.observableList(searchBookList));
        }
    }

    @FXML
    public void loadAllReader() {
        loadReaderInfo(null);
    }

    @FXML
    public void loadAllBook() {
        loadBookInfo(null);
    }

    @FXML
    public void loadReaderId() {
        String strId = TFReaderId.getText();
        if (!"".equals(strId)) {
            try {
                Integer id = Integer.valueOf(strId);
                BorrowCardService borrowCardService = new BorrowCardServiceImpl();
                BorrowCardModel borrowCard = borrowCardService.findBorrowCardByRID(id);
                if (borrowCard != null) {
                    loadReaderInfo(id);
                } else if (borrowCard == null) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Lỗi");
                    alert.setHeaderText("Error");
                    alert.setContentText("Không tìm thấy khách hàng");
                    alert.showAndWait();
                }

            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Lỗi");
                alert.setHeaderText("Error");
                alert.setContentText("Bạn cần phải nhập số");
                alert.showAndWait();
            }
        } else if ("".equals(strId)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Lỗi");
            alert.setHeaderText("Error");
            alert.setContentText("Bạn chưa nhập id");
            alert.showAndWait();
        }

    }

    @FXML
    public void loadBookId() {
        String strId = TFBookId.getText();
        int pass = 0;
        if (!"".equals(strId)) {
            try {
                Integer id = Integer.valueOf(strId);
                BookService bookService = new BookServiceImpl();

                Map<String, Object> g = new HashMap<>();
                g.put("id", id);

                List<BookModel> bookList = bookService.findBooks(g, null);

                for (BookModel book : bookList) {

                    if (book != null) {
                        loadBookInfo(id);
                        pass++;
                    }
                }
                if (pass == 0) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Lỗi");
                    alert.setHeaderText("Error");
                    alert.setContentText("Không tìm thấy sách");
                    alert.showAndWait();
                }

            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Lỗi");
                alert.setHeaderText("Error");
                alert.setContentText("Bạn cần phải nhập số");
                alert.showAndWait();
            }
        } else if ("".equals(strId)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Lỗi");
            alert.setHeaderText("Error");
            alert.setContentText("Bạn chưa nhập id");
            alert.showAndWait();
        }
    }

    @FXML
    public void switchForm(ActionEvent event) {
        if (event.getSource() == borrowBook_Btn) {
            borrowBook_viewForm.setVisible(true);
            searchBook_viewForm.setVisible(false);
            status_viewForm.setVisible(false);
            loanslip_viewForm.setVisible(false);
            returnBook_viewForm.setVisible(false);
        }
        if (event.getSource() == searchBook_Btn) {
            borrowBook_viewForm.setVisible(false);
            searchBook_viewForm.setVisible(true);
            status_viewForm.setVisible(false);
            loanslip_viewForm.setVisible(false);
            returnBook_viewForm.setVisible(false);
        }
        if (event.getSource() == status_Btn) {
            borrowBook_viewForm.setVisible(false);
            searchBook_viewForm.setVisible(false);
            status_viewForm.setVisible(true);
            loanslip_viewForm.setVisible(false);
            returnBook_viewForm.setVisible(false);
        }
        if (event.getSource() == createLoanslipBtn) {
            borrowBook_viewForm.setVisible(false);
            searchBook_viewForm.setVisible(false);
            status_viewForm.setVisible(false);
            loanslip_viewForm.setVisible(true);
            returnBook_viewForm.setVisible(false);
        }
        if (event.getSource() == returnBook_Btn) {
            borrowBook_viewForm.setVisible(false);
            searchBook_viewForm.setVisible(false);
            status_viewForm.setVisible(false);
            loanslip_viewForm.setVisible(false);
            returnBook_viewForm.setVisible(true);
        }
        if (event.getSource() == loanslip_exitBtn) {
            borrowBook_viewForm.setVisible(false);
            searchBook_viewForm.setVisible(false);
            status_viewForm.setVisible(true);
            loanslip_viewForm.setVisible(false);
            returnBook_viewForm.setVisible(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadReaderColumn();
        loadReaderInfo(null);
        loadBookColumn();
        loadBookInfo(null);
    }
}
