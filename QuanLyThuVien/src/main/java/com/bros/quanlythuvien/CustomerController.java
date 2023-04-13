/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien;

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
    private LoanSlipService loanSlipService = new LoanSlipServiceImpl();
    private ReaderRepository readerRepository = new ReaderRepositoryImpl();

//    Hàm
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

                            }
                        }
                        if (rs == false) {
                            book.setQuantity(1);
                            bookListCart.add(book);
                            System.out.println(" NAHN ROI NE1");

                        }
                        System.out.println(" NAHN ROI NE");

                    });
                    setGraphic(addButton);
                    setText(null);
                }
            }
        });

        TBRSearchBook.getColumns().addAll(colId, colName, colAuthor, colDescription, colPublishedYear, colPublishedPlace, colCategory, colLocation, buyColumn);
    }

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
                    bookListCart.remove(book);
                    tb_Cart.refresh();
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

    public void load_info_cart(List<BookModel> bookListCart, TableView<BookModel> tb_Cart, Integer page) {
        tb_Cart.setItems(FXCollections.observableList(bookListCart));
        tb_Cart.refresh();
    }

    public void info_reader(TableView<ReaderModel> infoCustomerTB, TextField infomation_name, ComboBox<String> infomation_gender, DatePicker infomation_birthDay) {
        ReaderModel rowData = infoCustomerTB.getSelectionModel().getSelectedItem();
        if (rowData != null) {
            infomation_name.setText(rowData.getFullname());
//            String gender = rowData.getGender();
            if (rowData.getGender() == null) {
                infomation_gender.setPromptText("Chọn giới tính");
            } else if (rowData.getGender().equals("Nam") || rowData.getGender().equals("Nữ")) {
                infomation_gender.setValue(rowData.getGender());
            } else {
                infomation_gender.setPromptText("Chọn giới tính");
            }
            if (rowData.getDateOfBirth() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate birthDay = LocalDate.parse(rowData.getDateOfBirth(), formatter);
                infomation_birthDay.setValue(birthDay);
            }
        }
    }
//    -------------------------------------------------

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
        loadReaderColumn(infoCustomerTB);
        loadLoanslipColumn(infoLoanSlipTB);
        loadGender();
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

    //Hiển thị combobox gender
    @FXML
    private void loadGender() {
        ReaderUtils.load_gender(infomation_gender);
    }

    //nhấn vào table reader xuất ra thông tin tương ứng
    @FXML
    private void InforReader() {
        info_reader(infoCustomerTB, infomation_name, infomation_gender, infomation_birthDay);
    }

    //Update thông tin reader
    @FXML
    private void updateReader() {
        ReaderModel reader = ReaderUtils.create_readerModel(readerId, infomation_name, infomation_gender, infomation_birthDay);
        boolean rs = readerService.updateReader(reader);
        if (rs) {
            MessageBoxUtils.AlertBox("INFORMATION", "Sửa đổi thành công", AlertType.INFORMATION);
            loadReaderInfo();
            infoCustomerTB.refresh();
        } else {
            MessageBoxUtils.AlertBox("ERROR", "Sửa đổi thất bại", AlertType.ERROR);
        }
    }

    private int totalQuantity = 0;

    @FXML
    public void createOnlineBook() {
        if (LScheckReader == 0) {
            MessageBoxUtils.AlertBox("Error", "Vui lòng nhấn nút kiểm tra trước", AlertType.ERROR);
        } else {
            for (BookModel book : bookListCart) {
                totalQuantity += book.getQuantity();
            }
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

    //hiển thị cột trong bảng reader
    @FXML
    private void loadReaderColumn(TableView<ReaderModel> infoCustomerTB) {
        ReaderUtils.load_reader_columns(infoCustomerTB);
    }

    //hiển thị dữ liệu bảng reader
    @FXML
    private void loadReaderInfo() {
        ReaderModel reader = readerService.findById(readerId);
        List<ReaderModel> readerList = new ArrayList<>();
        readerList.add(reader);
        this.infoCustomerTB.setItems(FXCollections.observableList(readerList));
    }

    @FXML
    private void loadLoanslipColumn(TableView<LoanSlipModel> infoLoanSlipTB) {
        LoanSlipUtils.load_loanslip_columns(infoLoanSlipTB);
    }

    @FXML
    private void loadLoanSlipInfo() {
        List<LoanSlipModel> loanSlipList = new ArrayList<>();
        loanSlipList = loanSlipService.findByCId(readerId);
        this.infoLoanSlipTB.setItems(FXCollections.observableList(loanSlipList));
    }

    @FXML
    private void clearCart() {
        bookListCart.clear();
        tb_Cart.refresh();
    }

    @FXML
    private void loadCartColumn(TableView<BookModel> tb_Cart, List<BookModel> bookListCart) {
        load_cart_column(tb_Cart, bookListCart);
    }

    @FXML
    public void loadInfoCart(List<BookModel> bookListCart, TableView<BookModel> tb_Cart, Integer page) {
        load_info_cart(bookListCart, tb_Cart, page);
    }

    @FXML
    private void loadSearchCategory() {
        Map<Integer, String> cateMap = readerRepository.loadCate(categoriesMap);
        ReaderUtils.load_cate(RsearchBook_category, cateMap);
    }

    @FXML
    private void loadRSearchBookColumn(TableView<BookModel> TBRSearchBook, List<BookModel> bookListCart
    ) {
        load_searchBook_column(TBRSearchBook, bookListCart);
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
