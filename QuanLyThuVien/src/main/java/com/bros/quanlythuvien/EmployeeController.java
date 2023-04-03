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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

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
    private TableView<BookModel> tbBook;

    @FXML
    private TextField TFBookId;

    @FXML
    private Button findAllBookBtn;

    @FXML
    private TextField LSBookAuthor;

    @FXML
    private TextField LSBookID;

    @FXML
    private TextField LSBookName;

    @FXML
    private TextField LSBorrowDate;

    @FXML
    private Button LSCreate;

    @FXML
    private TextField LSCustomerID;

    @FXML
    private TextField LSExpirationDate;

    @FXML
    private Button LsSearchBookBtn;

    @FXML
    private Button LsSearchReaderBtn;

    @FXML
    private TextField LSBookQuantity;

    @FXML
    private Button LsBookAdd;

    @FXML
    private Button LsBookList;

    @FXML
    private TableView<BookModel> LSTBBookList;

    @FXML
    private AnchorPane bookList_viewForm;

    @FXML
    private Button exitBookListBtn;
    
    BookService bookService;
//    @FXML
//    public ArrayList<BookModel> LSbookList = new ArrayList();
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
    private void loadBookInfo(Map<String, Object> searchMap, Integer page) {
        List<BookModel> searchBookList = bookService.findBooks(searchMap, page);
        this.tbBook.setItems(FXCollections.observableList(searchBookList));
    }

    @FXML
    public void loadAllReader() {
        loadReaderInfo(null);
    }

    @FXML
    public void loadAllBook() {
        loadBookInfo(null, null);
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
//                        loadBookInfo(id);
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
    private int LScheckReader = 0;

    @FXML
    private int LScheckBook = 0;

    @FXML
    public void checkReaderID() {
        ReaderService readerService = new ReaderServiceImpl();
        String sid = LSCustomerID.getText();
        int fail = 0;

        Connection connect = getConnection();

        if ("".equals(sid)) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Informatin");
            alert.setHeaderText("Lỗi nhập");
            alert.setContentText("Vui lòng nhập id vào");
            alert.showAndWait();
            LScheckReader = 0;
        } else {
            try {

                Integer id = Integer.valueOf(sid);

                try {
                    String sql = "select ExpiryDate from borrowcards where ReaderID =?;";
                    statement = connect.prepareStatement(sql);
                    statement.setInt(1, id);
                    result = statement.executeQuery();
                    if (result.next()) {

                        LocalDate expiryDate = result.getDate("ExpiryDate").toLocalDate(); // Lấy giá trị ExpiryDate từ ResultSet

                        LocalDate currentDate = LocalDate.now(); // Lấy ngày hiện tại

                        if (expiryDate.isBefore(currentDate)) {
                            // Nếu ExpiryDate đã hết hạn, thực hiện hành động tương ứng ở đây
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle("ERROR");
                            alert.setHeaderText("ERROR");
                            alert.setContentText("Thẻ thư viện đã hết hạn");
                            alert.showAndWait();
                        } else {
                            // Nếu ExpiryDate còn hạn, thực hiện hành động tương ứng ở đây
                            try {
                                String sql2 = "select CustomerID,isReturned, SUM(Quantity) as totalQuantity  from loanslip where CustomerID=? group by CustomerID,isReturned ;";
                                statement = connect.prepareStatement(sql2);
                                statement.setInt(1, id);
                                result = statement.executeQuery();
                                while (result.next()) {
                                    Integer isReturn = result.getInt("isReturned");
                                    if (isReturn == 0) {
                                        fail++;
                                        Alert alert = new Alert(AlertType.ERROR);
                                        alert.setTitle("ERROR");
                                        alert.setHeaderText("ERROR");
                                        alert.setContentText("Người dùng chưa trả sách");
                                        alert.showAndWait();
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            int LSpassReader = 0;
                            if (fail == 0) {
                                List<ReaderModel> readerList = readerService.findAll();
                                for (ReaderModel reader : readerList) {
                                    if (Objects.equals(reader.getId(), id)) {
                                        LSpassReader++;
                                    }
                                }

                                if (LSpassReader > 0) {
                                    Alert alert = new Alert(AlertType.INFORMATION);
                                    alert.setTitle("Success");
                                    alert.setHeaderText("Success");
                                    alert.setContentText("Người dùng có tồn tại");
                                    alert.showAndWait();
                                    LScheckReader = 1;
                                } else {

                                    Alert alert = new Alert(AlertType.ERROR);
                                    alert.setTitle("Error");
                                    alert.setHeaderText("Error");
                                    alert.setContentText("Người dùng không tồn tại");
                                    alert.showAndWait();
                                    LScheckReader = 0;
                                }
                            }

                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

//                        Connection connect = getConnection();
            } catch (NumberFormatException e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Lỗi nhập");
                alert.setContentText("Vui lòng chỉ nhập số");
                alert.showAndWait();
                LScheckReader = 0;
            }
        }
    }

    @FXML
    public void loadLSBook() {
        BookService bookService = new BookServiceImpl();
        String sid = LSBookID.getText();
        LSBookQuantity.setText("1");

        if ("".equals(sid)) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Informatin");
            alert.setHeaderText("Lỗi nhập");
            alert.setContentText("Vui lòng nhập id vào");
            alert.showAndWait();
            LScheckBook = 0;
        } else {
            try {
                int id = Integer.parseInt(sid);

                Map<String, Object> g = new HashMap<>();
                g.put("id", id);
                List<BookModel> bookList = bookService.findBooks(g, null);

                List<SearchBookModel> searchBookList = new ArrayList<>();

                for (BookModel book : bookList) {
                    SearchBookModel searchBook = new SearchBookModel(
                            book.getTitle(),
                            book.getAuthor()
                    );
                    searchBookList.add(searchBook);
                }

                if (!searchBookList.isEmpty()) {
                    SearchBookModel book = searchBookList.get(0);
                    LSBookName.setText(book.getTitle());
                    LSBookAuthor.setText(book.getAuthor());
                    LScheckBook = 1;

                } else {
                    LSBookName.setText("");
                    LSBookAuthor.setText("");
                    LScheckBook = 0;
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Lỗi nhập");
                alert.setContentText("Vui lòng chỉ nhập số");
                alert.showAndWait();
                LScheckBook = 0;
            }
        }
    }

    @FXML
    private ArrayList<BookModel> LSbookList = new ArrayList<>();

    @FXML
    private Integer countQuantity = 0;

    @FXML
    public void LSCreateBookList() {

        if (LScheckBook > 0 && LScheckReader > 0) {
            String strbookid = LSBookID.getText();
            Integer bookid = Integer.valueOf(strbookid);
            String name = LSBookName.getText();
            String author = LSBookAuthor.getText();
            String strquantity = LSBookQuantity.getText();
            Integer quantity = Integer.valueOf(strquantity);

            if (quantity <= 5) {

                countQuantity += quantity;
                if (countQuantity <= 5) {

                    boolean bookExists = false;
                    for (BookModel book : LSbookList) {
                        if (Objects.equals(book.getId(), bookid)) {
                            book.setQuantity(book.getQuantity() + quantity);
                            bookExists = true;
                            break;
                        }
                    }

                    if (!bookExists) {
                        BookModel newBook = new BookModel(bookid, name, author, quantity);
                        LSbookList.add(newBook);
                    }
                    LSTBBookList.refresh();

                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("Thành công");
                    alert.setContentText("Đã thêm sách vào danh sách phiếu mượn");
                    alert.showAndWait();
                } else {
                    countQuantity -= quantity;
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Lỗi");
                    alert.setContentText("Tổng số lượng mượn đã lớn hơn 5");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Lỗi");
                alert.setContentText("Số lượng không được lớn hơn 5");
                alert.showAndWait();
            }

        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Lỗi");
            alert.setContentText("Vui lòng kiểm tra sách và người dùng trước khi thêm");
            alert.showAndWait();
        }
        LScheckBook = 0;
    }

    public class DeleteButtonTableCell<S> extends TableCell<S, Boolean> {

        private final Button deleteButton;
        private final List<S> itemList;

        public DeleteButtonTableCell(List<S> itemList) {
            this.deleteButton = new Button("Delete");
            this.itemList = itemList;

            this.deleteButton.setOnAction(event -> {
                System.out.println("nut da duoc click");
                S currentItem = (S) getTableRow().getItem();
                if (currentItem != null) {
                    itemList.remove(currentItem);
                    loadLSBookListInfo();
                    LSTBBookList.refresh();
                }
            });
        }

        @Override
        protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(deleteButton);
                setText(null);
            }
        }
    }

    @FXML
    public void loadLSBookListColumn() {
        TableColumn<BookModel, Integer> bookIdColumn = new TableColumn<>("Book ID");
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<BookModel, Integer>("id"));

        TableColumn<BookModel, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<BookModel, String>("title"));

        TableColumn<BookModel, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(new PropertyValueFactory<BookModel, String>("author"));

        TableColumn<BookModel, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<BookModel, Integer>("quantity"));

        TableColumn<BookModel, Boolean> deleteColumn = new TableColumn<>("Delete");
        deleteColumn.setCellValueFactory(cellData -> new SimpleBooleanProperty(true));
        deleteColumn.setCellFactory(column -> new DeleteButtonTableCell<BookModel>(LSbookList));

        LSTBBookList.getColumns().addAll(bookIdColumn, nameColumn, authorColumn, quantityColumn, deleteColumn);
    }

    @FXML
    public void loadLSBookListInfo() {
        ObservableList<BookModel> bookObservableList = FXCollections.observableArrayList();
        bookObservableList.addAll(LSbookList);
        LSTBBookList.setItems(bookObservableList);
    }

    @FXML
    public void loadLSDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String string = currentDate.format(formatter);
        LSBorrowDate.setText(string);
        LocalDate returnDate = currentDate.plusDays(30);
        String returnDateString = returnDate.format(formatter);
        LSExpirationDate.setText(returnDateString);
    }

    @FXML
    public void creatLoanSlip() {

        if (LScheckReader == 1 && !LSbookList.isEmpty()) {
            Connection connect = getConnection();
            for (BookModel book : LSbookList) {
                try {
                    String sql = "INSERT INTO librarymanagement.loanslip (CustomerID, BookID, BookName, BookAuthor, BorrowedDate, ExpirationDate,Quantity,isReturned) VALUES (?, ?, ?, ?, ?, ?,?,0);";
                    statement = connect.prepareStatement(sql);
                    statement.setString(1, LSCustomerID.getText());
                    statement.setInt(2, book.getId());
                    statement.setString(3, book.getTitle());
                    statement.setString(4, book.getAuthor());
                    java.sql.Date borrowDate = java.sql.Date.valueOf(LSBorrowDate.getText());
                    statement.setDate(5, borrowDate);
                    java.sql.Date expirationDate = java.sql.Date.valueOf(LSExpirationDate.getText());
                    statement.setDate(6, expirationDate);
                    statement.setInt(7, book.getQuantity());
                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        countQuantity = 0;
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Informatin");
                        alert.setHeaderText("Success");
                        alert.setContentText("Thêm thành công");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText("ERROR");
                        alert.setContentText("Thêm thất bại");
                        alert.showAndWait();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Khách hàng không tồn tại hoặc bạn chưa thêm sách để tạo phiếu mượn");
            alert.showAndWait();
        }
    }

    @FXML
    public void clearArray() {
        LSbookList.clear();
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
            borrowBook_viewForm.setVisible(true);
            loanslip_viewForm.setVisible(false);
        }
        if (event.getSource() == LsBookList) {
            bookList_viewForm.setVisible(true);
            loanslip_viewForm.setVisible(false);
        }
        if (event.getSource() == exitBookListBtn) {
            bookList_viewForm.setVisible(false);
            loanslip_viewForm.setVisible(true);
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
        loadReaderColumn();
        loadReaderInfo(null);
        loadBookColumn();
        loadBookInfo(null, null);
        loadLSDate();
        loadLSBookListColumn();

    }
}
