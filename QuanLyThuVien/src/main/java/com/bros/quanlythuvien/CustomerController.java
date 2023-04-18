/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien;

import com.bros.quanlythuvien.model.AccountModel;
import com.bros.quanlythuvien.model.BookModel;
import com.bros.quanlythuvien.model.LoanSlipModel;
import com.bros.quanlythuvien.model.ReaderModel;
import com.bros.quanlythuvien.repository.ReaderRepository;
import com.bros.quanlythuvien.repository.impl.ReaderRepositoryImpl;
import com.bros.quanlythuvien.service.BookService;
import com.bros.quanlythuvien.service.LoanSlipService;
import com.bros.quanlythuvien.service.ReaderService;
import com.bros.quanlythuvien.service.impl.BookServiceImpl;
import com.bros.quanlythuvien.service.impl.LoanSlipServiceImpl;
import com.bros.quanlythuvien.service.impl.ReaderServiceImpl;
import com.bros.quanlythuvien.utils.LoanSlipUtils;
import com.bros.quanlythuvien.utils.MessageBoxUtils;
import com.bros.quanlythuvien.utils.ReaderUtils;
import java.io.IOException;
import java.net.URL;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    private ComboBox<String> infomation_gender;

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
    private TableView<LoanSlipModel> infoLoanSlipTB;

    @FXML
    private TextField infomation_phone;

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
    private LoanSlipService loanSlipService = new LoanSlipServiceImpl();
    private ReaderRepository readerRepository = new ReaderRepositoryImpl();
    private Map<Integer, String> categoriesMap = new HashMap<>();
    List<BookModel> bookListCart = new ArrayList<>();
    private Integer readerId;
    private int totalQuan = 0;
    private int LScheckReader = 0;

    // @Param
    // @Return
    // @Description
//    Hàm
    // @Param: TableView TBRSearchBook (trang Search Book của Reader), List<BookModel> bookListCart (danh sách sách mà Reader muốn mua (nhấn vào nút Add to Cart))
    // @Return: Không
    // @Description: hàm load các cột trong TableView TBRSearchBook (trang Search Book của Reader),khi người dùng nhấn nút Add to Cart thì sẽ thêm sách vào màng bookListCart
    public void load_searchBook_column(TableView<BookModel> TBRSearchBook, List<BookModel> bookListCart) {
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
        colCategory.setCellValueFactory(new PropertyValueFactory("categoryValue"));
        TableColumn colLocation = new TableColumn("Location");
        colLocation.setCellValueFactory(new PropertyValueFactory("location"));
        TableColumn<BookModel, Boolean> buyColumn = new TableColumn<>("Buy");
        buyColumn.setCellValueFactory(cellData -> new SimpleBooleanProperty(true));
        buyColumn.setCellFactory(column -> new TableCell<BookModel, Boolean>() {
            final Button addButton = new Button("Add To Cart");

            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    addButton.setOnAction(event -> {
                        BookModel book = getTableView().getItems().get(getIndex());
                        boolean rs = false;
                        for (BookModel b : bookListCart) {
                            if (Objects.equals(b.getId(), book.getId())) {
                                b.setQuantity(b.getQuantity() + 1);
                                rs = true;
                                totalQuan++;
                                countQuantityOnCart();

                            }
                        }
                        if (rs == false) {
                            book.setQuantity(1);
                            bookListCart.add(book);
                            totalQuan++;
                            countQuantityOnCart();

                        }
                    });
                    setGraphic(addButton);
                    setText(null);
                }
            }
        });

        TBRSearchBook.getColumns().addAll(colId, colName, colAuthor, colDescription, colPublishedYear, colPublishedPlace, colCategory, colLocation, buyColumn);
    }

    // @Param: TableView tb_Cart (trang Cart của Reader), List<BookModel> bookListCart (danh sách sách mà Reader muốn mua (đã nhấn vào nút Add to Cart ở trang Search Book))
    // @Return: Không
    // @Description: hàm load các cột trong TableView tb_Cart (trang Cart của Reader)
    public void load_cart_column(TableView<BookModel> tb_Cart, List<BookModel> bookListCart) {
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
        colCategory.setCellValueFactory(new PropertyValueFactory("categoryValue"));
        TableColumn colQuantity = new TableColumn("Quantity");
        colQuantity.setCellValueFactory(new PropertyValueFactory("quantity"));
        TableColumn<BookModel, Void> colDelete = new TableColumn<>("Delete");

        colDelete.setCellFactory(param -> new TableCell<BookModel, Void>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(event -> {
                    BookModel book = getTableView().getItems().get(getIndex());
                    int currentQuantity = book.getQuantity();
                    if (currentQuantity > 0) {
                        book.setQuantity(currentQuantity - 1);
                        colQuantity.getTableView().refresh();
                        totalQuan--;
                        countQuantityOnCart();

                    }
                    if (book.getQuantity() == 0) {
                        bookListCart.remove(book);

                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });
        tb_Cart.getColumns().addAll(colName, colAuthor, colDescription, colPublishedYear, colPublishedPlace, colCategory, colQuantity, colDelete);
    }

    // @Param: TableView tb_Cart (trang Cart của Reader), List<BookModel> bookListCart (danh sách sách mà Reader muốn mua (đã nhấn vào nút Add to Cart ở trang Search Book)), Integer page phân trang
    // @Return: Không
    // @Description: Lấy các sách được lưu trong mảng bookListCart danh sách sách mà Reader muốn mua (đã nhấn vào nút Add to Cart ở trang Search Book) truyền vào TableView<BookModel> tb_Cart (trang Cart của Reader)
    public void load_info_cart(List<BookModel> bookListCart, TableView<BookModel> tb_Cart, Integer page) {
        tb_Cart.setItems(FXCollections.observableList(bookListCart));
        tb_Cart.refresh();
    }

//    -------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bookService = new BookServiceImpl();
        setReaderId(readerId);
        loadRSearchBookColumn(TBRSearchBook, bookListCart);
        // Hàm này lỗi chỗ converter --> fix khỏi đóng conn hơi kì :v
        loadRSearchBookInfo(null, null);
        loadSearchCategory();
        loadCartColumn(tb_Cart, bookListCart);
        loadLoanslipColumn(infoLoanSlipTB);
        loadGender();
        countQuantityOnCart();
        searchBook_Btn.requestFocus();
        searchBook_Btn.fire();
    }

    // @Param: Integer id được lấy từ ReaderID khi login được dò trong account trong database 
    // @Return: không
    // @Description: Gán biến toàn cục readerId bằng đúng ReaderID trong account mà ta đăng nhập
    @FXML
    public void setReaderId(Integer id) {
        this.readerId = id;
    }

    // @Param: Không
    // @Return: không
    // @Description: Gọi hàm load_gender trong ReaderUtils để truyền dữ liệu gender vào ComboBox infomation_gender(trang infomation của Reader) 
    @FXML
    private void loadGender() {
        ReaderUtils.load_gender(infomation_gender);
    }

    // @Param: không
    // @Return: không
    // @Description: Nhấn nút Update thông tin reader (trang infomation của Reader)
    @FXML
    private void updateReader() {
        String phone = infomation_phone.getText();
//        Số điện thoại bắt buộc phải 11 số và bắt đầu bằng số 0
        if (phone.matches("\\d{11}") && phone.startsWith("0")) {
            ReaderModel reader = ReaderUtils.create_readerModel(readerId, infomation_name, infomation_gender, infomation_birthDay, infomation_phone);
            boolean rs = readerService.updateReader(reader);
            if (rs) {
                MessageBoxUtils.AlertBox("INFORMATION", "Sửa đổi thành công", AlertType.INFORMATION);
                loadReaderInfo();
            } else {
                MessageBoxUtils.AlertBox("ERROR", "Sửa đổi thất bại", AlertType.ERROR);
            }
        } else {
            MessageBoxUtils.AlertBox("ERROR", "Vui lòng nhập đủ 11 số và bắt đầu phải là số 0", Alert.AlertType.ERROR);
        }

    }

    // @Param: không
    // @Return: không
    // @Description: Hiển thị số lượng sách người dùng đã thêm vào Cart (hiển thị ở Cart_Btn(nút nhấn chuyển trang Cart))
    @FXML
    public void countQuantityOnCart() {
        Cart_Btn.setText("Cart  " + totalQuan);
    }

    // @Param: không
    // @Return: không
    // @Description: Nhấn nút Submit trang Cart của Reader để tạo phiếu mượn
    @FXML
    public void createOnlineBook() {
        int totalQuantity = 0;
//        Kiểm tra người dùng có hợp lệ hay không
        int check = readerService.checkReader(readerId);
        if (check == 0) {
            LScheckReader = 0;
        } else {
            LScheckReader = 1;
//            Lấy số lượng sách đã có trong Cart
            for (BookModel book : bookListCart) {
                totalQuantity += book.getQuantity();
            }
//            Kiểm tra số lượng sách trong Cart
            if (totalQuantity <= 5) {
                String strReaderId = Integer.toString(readerId);
                loanSlipService.creatLoanSlip(bookListCart, LScheckReader, strReaderId, 0);
                clearCart();
            } else {
                MessageBoxUtils.AlertBox("Error", "Không thể mượn quá 5 cuốn sách", AlertType.ERROR);
            }
        }
        totalQuantity = 0;
    }

    // @Param: không
    // @Return: không
    // @Description: Hiển thị dữ liệu reader tự động vào trang Infomation dựa theo readerId lấy từ lúc login 
    @FXML
    private void loadReaderInfo() {
//        Tìm reader dựa theo readerId
        ReaderModel reader = readerService.findById(readerId);
//        Tìm account dựa theo readerId
        AccountModel readerEmail = readerService.findAccountByRId(readerId);
        infomation_name.setText(reader.getFullname());
        infomation_email.setText(readerEmail.getEmail());
        infomation_phone.setText(reader.getPhone());
        if (null == reader.getGender() || "".equals(reader.getGender())) {
            infomation_gender.setValue("Chọn giới tính");
        } else {
            infomation_gender.setValue(reader.getGender());
        }
        if (reader.getDateOfBirth() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate birthDay = LocalDate.parse(reader.getDateOfBirth(), formatter);
            infomation_birthDay.setValue(birthDay);
        } else {
            infomation_birthDay.setValue(null);
        }
    }

    // @Param: TableView<LoanSlipModel> infoLoanSlipTB (table trong trang Infomation)
    // @Return: không
    // @Description: Hiển thị các cột trong TableView<LoanSlipModel> infoLoanSlipTB (table trong trang Infomation)
    @FXML
    private void loadLoanslipColumn(TableView<LoanSlipModel> infoLoanSlipTB) {
        LoanSlipUtils.load_loanslip_columns(infoLoanSlipTB);
    }

    // @Param: không
    // @Return: không
    // @Description: Hiển thị các phiếu mượn mà reader này đã mượn dựa theo readerId lấy từ lúc login vào TableView<LoanSlipModel> infoLoanSlipTB (table trong trang Infomation)
    @FXML
    private void loadLoanSlipInfo() {
        List<LoanSlipModel> loanSlipList = new ArrayList<>();
//        Tìm các phiếu mượn của Reader
        loanSlipList = loanSlipService.findByCId(readerId);
        this.infoLoanSlipTB.setItems(FXCollections.observableList(loanSlipList));
    }

    // @Param: không
    // @Return: không
    // @Description: Xóa rỗng bookListCart (danh sách đặt sách của Reader), tải lại tb_Cart (table trang Cart), đặt lại số lượng sách Reader đặt = 0, gọi hàm countQuantityOnCart để tải lại số kế bên Cart_btn
    @FXML
    private void clearCart() {
        bookListCart.clear();
        tb_Cart.refresh();
        totalQuan = 0;
        countQuantityOnCart();

    }

    // @Param: TableView tb_Cart (trang Cart của Reader), List<BookModel> bookListCart (danh sách sách mà Reader muốn mua (đã nhấn vào nút Add to Cart ở trang Search Book))
    // @Return: Không
    // @Description: gọi hàm load các cột trong TableView tb_Cart (trang Cart của Reader)
    @FXML
    private void loadCartColumn(TableView<BookModel> tb_Cart, List<BookModel> bookListCart) {
        load_cart_column(tb_Cart, bookListCart);
    }

    // @Param: TableView tb_Cart (trang Cart của Reader), List<BookModel> bookListCart (danh sách sách mà Reader muốn mua (đã nhấn vào nút Add to Cart ở trang Search Book)), Integer page phân trang
    // @Return: Không
    // @Description: Lấy các sách được lưu trong mảng bookListCart danh sách sách mà Reader muốn mua (đã nhấn vào nút Add to Cart ở trang Search Book) truyền vào TableView<BookModel> tb_Cart (trang Cart của Reader)
    @FXML
    public void loadInfoCart(List<BookModel> bookListCart, TableView<BookModel> tb_Cart, Integer page) {
        load_info_cart(bookListCart, tb_Cart, page);
    }

    // @Param: Không
    // @Return: không
    // @Description: Gọi hàm load_cate trong ReaderUtils để truyền dữ liệu gender vào ComboBox RsearchBook_category(trang Search Book của Reader) 
    @FXML
    private void loadSearchCategory() {
//        Tìm dữ liệu các category trong database
        Map<Integer, String> cateMap = readerRepository.loadCate(categoriesMap);
//        Load dữ liệu vào RsearchBook_category
        ReaderUtils.load_cate(RsearchBook_category, cateMap);
    }

    // @Param: TableView<BookModel> TBRSearchBook (trang Search Book của reader),List<BookModel> bookListCart danh sách đặt sách của Reader
    // @Return: không
    // @Description: Gọi hàm load_searchBook_column load các cột trong TableView TBRSearchBook (trang Search Book của Reader), khi người dùng nhấn nút Add to Cart thì sẽ thêm sách vào màng bookListCart
    @FXML
    private void loadRSearchBookColumn(TableView<BookModel> TBRSearchBook, List<BookModel> bookListCart
    ) {
        load_searchBook_column(TBRSearchBook, bookListCart);
    }

    // @Param: searchMap chứa thông tin các sách có trong database
    // @Return: không
    // @Description: tải tất cả sách lên trang TBRSearchBook (trang Search Book của Reader)
    @FXML
    private void loadRSearchBookInfo(Map<String, Object> searchMap, Integer page
    ) {
//        Tải dữ liệu tất cả sách có trong database
        List<BookModel> searchBookList = bookService.findBooks(searchMap, page);
        this.TBRSearchBook.setItems(FXCollections.observableList(searchBookList));
    }

    // @Param: không
    // @Return: không
    // @Description: Bắt sự kiện nhấn enter tìm kiếm trong trang Search Book của Reader
    @FXML
    private void onEnterPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            loadRSearch();
        }
    }

    // @Param: không
    // @Return: không
    // @Description: Nhấn nút Search trong trang Search Book của Reader để gọi hàm getSearchMap tìm kiếm sách
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

     // @Param: không
    // @Return: không
    // @Description: chuyển đổi giữa các trang
    @FXML
    public void switchForm(ActionEvent event) {
        String style = "-fx-background-color: #93773e; -fx-text-fill: #fff; -fx-font-weight: bold; -fx-font-style: italic;";
        if (event.getSource() == information_Btn) {
            loadReaderInfo();
            loadLoanSlipInfo();
            information_viewForm.setVisible(true);
            information_Btn.setStyle(style);
            AnchorPane[] allForms = {searchBook_viewForm, cart_viewForm};
            Button[] allButtons = {searchBook_Btn, Cart_Btn};
            for (AnchorPane allForm : allForms) {
                allForm.setVisible(false);
            }
            for (Button allButton : allButtons) {
                allButton.setStyle("");
            }
        }
        if (event.getSource() == searchBook_Btn) {
            searchBook_viewForm.setVisible(true);
            searchBook_Btn.setStyle(style);
            AnchorPane[] allForms = {information_viewForm, cart_viewForm};
            Button[] allButtons = {information_Btn, Cart_Btn};
            for (AnchorPane allForm : allForms) {
                allForm.setVisible(false);
            }
            for (Button allButton : allButtons) {
                allButton.setStyle("");
            }
        }
        if (event.getSource() == Cart_Btn) {
            cart_viewForm.setVisible(true);
            loadInfoCart(bookListCart, tb_Cart, null);
            Cart_Btn.setStyle(style);
            AnchorPane[] allForms = {information_viewForm, searchBook_viewForm};
            Button[] allButtons = {information_Btn, searchBook_Btn};
            for (AnchorPane allForm : allForms) {
                allForm.setVisible(false);
            }
            for (Button allButton : allButtons) {
                allButton.setStyle("");
            }
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
