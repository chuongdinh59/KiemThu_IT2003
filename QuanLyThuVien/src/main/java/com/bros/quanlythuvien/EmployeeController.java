/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien;

import com.bros.quanlythuvien.model.AccountModel;
import com.bros.quanlythuvien.model.BookModel;
import com.bros.quanlythuvien.model.BorrowCardModel;
import com.bros.quanlythuvien.model.LoanSlipModel;
import com.bros.quanlythuvien.model.ReaderModel;
import com.bros.quanlythuvien.model.SearchBookModel;
import com.bros.quanlythuvien.repository.ReaderRepository;
import com.bros.quanlythuvien.repository.impl.ReaderRepositoryImpl;
import com.bros.quanlythuvien.service.BookService;
import com.bros.quanlythuvien.service.BorrowCardService;
import com.bros.quanlythuvien.service.LoanSlipService;
import com.bros.quanlythuvien.service.ReaderService;
import com.bros.quanlythuvien.service.impl.BookServiceImpl;
import com.bros.quanlythuvien.service.impl.BorrowCardServiceImpl;
import com.bros.quanlythuvien.service.impl.EmailService;
import com.bros.quanlythuvien.service.impl.LoanSlipServiceImpl;
import com.bros.quanlythuvien.service.impl.ReaderServiceImpl;
import com.bros.quanlythuvien.utils.LoanSlipUtils;
import com.bros.quanlythuvien.utils.MessageBoxUtils;
import com.bros.quanlythuvien.utils.ReaderUtils;
import com.bros.quanlythuvien.utils.ValidateUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.mail.MessagingException;

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
    private ComboBox<String> searchBook_category;

    @FXML
    private TextField searchBook_name;

    @FXML
    private TextField searchBook_publishedYear;

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
    private TableView<BorrowCardModel> tbReader;

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

    @FXML
    private TableView<BookModel> tb_SearchBook;

    @FXML
    private Button returnFindallLoanslipBtn;

    @FXML
    private Button returnFindallReaderBtn;

    @FXML
    private TableView<LoanSlipModel> returnLoanslipTB;

    @FXML
    private TableView<LoanSlipModel> statusBookTB;

    @FXML
    private TextField returnLoanslipTF;

    @FXML
    private TableView<BorrowCardModel> returnReaderTB;

    @FXML
    private TextField returnReaderTF;

    @FXML
    private Button returnSearchLoanslipBtn;

    @FXML
    private Button returnSearchReaderBtn;

    @FXML
    private TextField statusBookTF;

    @FXML
    private TextField statusReaderTF;

    @FXML
    private Button CreateBorrowCard_Btn;

    @FXML
    private Button reader_Btn;

    @FXML
    private AnchorPane readerStatus_viewForm;

    @FXML
    private TableView<ReaderModel> readerStatusTB;

    @FXML
    private int LScheckReader = 0;

    @FXML
    private int LScheckBook = 0;

    @FXML
    private ArrayList<BookModel> LSbookList = new ArrayList<>();

    @FXML
    private Integer countQuantity = 0;

    private BookService bookService;
    private Map<Integer, String> categoriesMap = new HashMap<>();
    private ReaderService readerService = new ReaderServiceImpl();
    private BorrowCardService borrowCardService;
    private LoanSlipService loanSlipService = new LoanSlipServiceImpl();
    private ReaderRepository readerRepository = new ReaderRepositoryImpl();

    // @params tableview search book
    // @return khong return 
    // @description ham render giao dien nao 
    //    Hàm
    public void load_book_columns(TableView<BookModel> tb_SearchBook) {
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
        TableColumn colquantity = new TableColumn("Quantity");
        colquantity.setCellValueFactory(new PropertyValueFactory("quantity"));

        tb_SearchBook.getColumns().addAll(colId, colName, colAuthor, colDescription, colPublishedYear, colPublishedPlace, colCategory, colLocation, colquantity);
    }

    public void load_reader_columns(TableView<BorrowCardModel> tbReader) {
        TableColumn colRId = new TableColumn("ReaderId");
        colRId.setCellValueFactory(new PropertyValueFactory("readerID"));
        TableColumn colId = new TableColumn("BorrowCardID");
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn colFName = new TableColumn("FullName");
        colFName.setCellValueFactory(new PropertyValueFactory("fullName"));
        TableColumn colIssuedDate = new TableColumn("IssuedDate");
        colIssuedDate.setCellValueFactory(new PropertyValueFactory("issuedDate"));
        TableColumn colExpiredDate = new TableColumn("ExpiredDate");
        colExpiredDate.setCellValueFactory(new PropertyValueFactory("expiredDate"));

        tbReader.getColumns().addAll(colId, colRId, colFName, colIssuedDate, colExpiredDate);
    }

    public void load_reader_infos(Integer id, TableView<BorrowCardModel> tbReader) {
        List<BorrowCardModel> readerBorrowCardList = new ArrayList<>();
        if (id != null) {
            BorrowCardModel borrowCard = borrowCardService.findBorrowCardByRID(id);
            ReaderModel reader = readerService.findById(borrowCard.getReaderID());
            borrowCard.setFullName(reader.getFullname());
            readerBorrowCardList.add(borrowCard);
        } else {
            List<BorrowCardModel> borrowCardModels = borrowCardService.findAll();
            for (BorrowCardModel b : borrowCardModels) {
                readerBorrowCardList.add(b);
            }
        }
        tbReader.setItems(FXCollections.observableList(readerBorrowCardList));
    }
//    -----------------------------

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

    // hiển thị dữ liệu ô thể loại trang tìm kiếm
    @FXML
    public void loadCate() {
        Map<Integer, String> cateMap = readerRepository.loadCate(categoriesMap);
        ReaderUtils.load_cate(searchBook_category, cateMap);
    }

    // hiển thị cột của bảng trang tìm kiếm
    @FXML
    public void loadSearchBookColumn(TableView<BookModel> tb_SearchBook) {
        load_book_columns(tb_SearchBook);
    }

    //hiển thị cột trong bảng reader trang reader
    @FXML
    private void loadReaderStatusColumn() {
        ReaderUtils.load_reader_columns(readerStatusTB);
    }

    //hiển thị dữ liệu bảng reader trang reader
    @FXML
    private void loadReaderStatusInfo() {
        List<ReaderModel> readerList = readerService.findReaderNotHaveBorrowCard();
        this.readerStatusTB.setItems(FXCollections.observableList(readerList));
    }

    // hiển thị dữ liệu trong bảng trang tìm kiếm
    @FXML
    private void loadSearchBookInfo(Map<String, Object> searchMap, Integer page
    ) {
        List<BookModel> searchBookList = bookService.findBooks(searchMap, page);
        this.tb_SearchBook.setItems(FXCollections.observableList(searchBookList));
    }

    //    Bắt sự kiện nhấn enter tìm kiếm
    @FXML
    private void onEnterPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            loadRSearch();
        }
    }

    // xử lý nút tìm kiếm trang search
    @FXML
    private void loadRSearch() {
        String strTitle = searchBook_name.getText();
        String strAuthor = searchBook_author.getText();
        String selectedCategory = searchBook_category.getValue();
        Integer cateID = null;
        for (Map.Entry<Integer, String> entry : categoriesMap.entrySet()) {
            if (entry.getValue().equals(selectedCategory)) {
                cateID = entry.getKey();
                break;
            }
        }
        String strPublish = searchBook_publishedYear.getText();
        Map<String, Object> searchMap = bookService.getSearchMap(strTitle, strAuthor, cateID, strPublish);
        loadSearchBookInfo(searchMap, null);
    }

    //hiển thị cột trong bảng reader trong trang borrow
    @FXML
    private void loadReaderColumn(TableView<BorrowCardModel> tbReader) {
        load_reader_columns(tbReader);
    }

    //hiển thị cột bảng book trong trang borrow
    @FXML
    private void loadBookColumn(TableView<BookModel> tbBook) {
        load_book_columns(tbBook);
    }

    // T sửa thành nếu có id thì tìm theo id nếu không có id tìm tất cả,
    // thấy khúc này sai ý m thì kêu t fix
    //***
    //***
    //***
    // hiển thị dữ liệu bảng reader trong trang borrow
    @FXML
    private void loadReaderInfo(Integer id, TableView<BorrowCardModel> tbReader) {
        load_reader_infos(id, tbReader);
    }

    //hiển thị dữ liệu bảng book trong trang borrow
    private void loadBookInfo(Map<String, Object> searchMap, Integer page) {
        List<BookModel> searchBookList = bookService.findBooks(searchMap, page);
        this.tbBook.setItems(FXCollections.observableList(searchBookList));
    }

    //hiển thị dữ liệu bảng loanslip trong trang return
    private void loadLoanslipInfo(TableView<LoanSlipModel> returnLoanslipTB) {
        List<LoanSlipModel> LoanslipList = loanSlipService.loadLoanslipInfo();
        returnLoanslipTB.setItems(FXCollections.observableList(LoanslipList));
    }

    //xử lý trả sách trong phiếu mượn
    @FXML
    private void returnBookReturn() {
        SelectionModel<LoanSlipModel> selectionModel = returnLoanslipTB.getSelectionModel();
        LoanSlipModel selectedLoanSlip = selectionModel.getSelectedItem();
        if (selectedLoanSlip != null) {
            if (selectedLoanSlip.getIsOnline() == 0) {
                Alert confirm = new Alert(AlertType.CONFIRMATION);
                confirm.setTitle("Thông báo");
                confirm.setContentText("Khách hàng chưa nhận sách, nếu tiếp tục sẽ hủy đơn");
                Optional<ButtonType> result = confirm.showAndWait();
                if (result.get() == ButtonType.OK) {
                    loanSlipService.updateBook(selectedLoanSlip);
                    loadLoanslipInfo(returnLoanslipTB);
                    returnLoanslipTB.refresh();
                }
            } else {
                Integer rs = loanSlipService.updateBook(selectedLoanSlip);
                if (rs > 0) {
                    MessageBoxUtils.AlertBox("ERROR", "Bạn đã trễ hạn " + rs + " ngày và tiền phạt là: " + 5000 * rs + " VNĐ", Alert.AlertType.ERROR);
                } else if (rs == -2) {
                    MessageBoxUtils.AlertBox("ERROR", "Sách đã được trả!!", Alert.AlertType.ERROR);
                } else if (rs == 0) {
                    MessageBoxUtils.AlertBox("INFORMATION", "Trả sách thành công", Alert.AlertType.INFORMATION);
                } else {
                    MessageBoxUtils.AlertBox("ERROR", "Trả sách thất bại", Alert.AlertType.ERROR);
                }
                loadLoanslipInfo(returnLoanslipTB);
                returnLoanslipTB.refresh();
            }

        }
    }

    //xử lý tạo borrowCard trang reader
    @FXML
    private void CreateBorrowCard() {
        SelectionModel<ReaderModel> selectionModel = readerStatusTB.getSelectionModel();
        ReaderModel reader = selectionModel.getSelectedItem();
        if (reader != null) {
            Integer id = reader.getId();
            AccountModel account = readerService.findAccountByRId(id);
            System.out.print("account email: " + account.getEmail());
            boolean rs = borrowCardService.createBorrowCard(id);
            if (rs) {
                loadReaderStatusInfo();
                String content = "Duyệt thẻ thư viện: " + reader.getFullname() + "\n"
                        + "Nếu có thắc mắt liên hệ hoianhemlambaitapkiemthu@gmail.com hoặc hotline 113";
                MessageBoxUtils.AlertBox("INFORMATION", "Tạo thẻ thư viện thành công", AlertType.INFORMATION);
                // Run email service in a new thread
                new Thread(() -> {
                    try {
                        EmailService sm = new EmailService();
                        sm.sendEmail(account.getEmail(),
                                "Thư viện đại học Mở - Thông báo duyệt thẻ thư viện thành công", content);
                    } catch (MessagingException | UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }).start();
            } else {
                MessageBoxUtils.AlertBox("ERROR", "Tạo thẻ thư viện thất bại", AlertType.ERROR);
            }
        }
    }

    //Xử lý giao sách trong phiếu mượn trang return
    @FXML
    private void returnBookGive() {
        SelectionModel<LoanSlipModel> selectionModel = returnLoanslipTB.getSelectionModel();
        LoanSlipModel selectedLoanSlip = selectionModel.getSelectedItem();
        if (selectedLoanSlip != null) {
            int rs = loanSlipService.updateBookGive(selectedLoanSlip);
            if (rs == 0) {
                MessageBoxUtils.AlertBox("ERROR", "Sách đã được lấy!!", Alert.AlertType.ERROR);
            } else if (rs == 1) {
                MessageBoxUtils.AlertBox("INFORMATION", "Trao sách thành công", Alert.AlertType.INFORMATION);

            } else {
                MessageBoxUtils.AlertBox("ERROR", "Trao sách thất bại", Alert.AlertType.ERROR);

            }
            loadLoanslipInfo(returnLoanslipTB);
            returnLoanslipTB.refresh();
        }
    }

    //xử lý nút findall trong bảng reader trang borrow
    @FXML
    public void loadAllReader() {
        loadReaderInfo(null, tbReader);
    }

    //xử lý nút findall trong bảng reader trang return
    @FXML
    public void loadAllReturnReader() {
        loadReaderInfo(null, returnReaderTB);
    }

    //xử lý nút findall trong bảng book trang borrow
    @FXML
    public void loadAllBook() {
        loadBookInfo(null, null);
    }

    //xử lý nút findall trong bảng loanslip trang return
    @FXML
    public void loadAllLoanSlip() {
        loadLoanslipInfo(returnLoanslipTB);
    }

    //xử lý nút tìm kiếm book trang status
    @FXML
    private void loadStatusBook() {
        List<LoanSlipModel> loanSlipList = new ArrayList<>();
        if ("".equals(statusBookTF.getText()) && "".equals(statusReaderTF.getText())) {
            loadLoanslipInfo(statusBookTB);
        } else {
            try {
                Integer bookID = null;
                Integer readerID = null;
                if (ValidateUtils.isNotBlank(statusBookTF.getText())) {
                    bookID = Integer.parseInt(statusBookTF.getText());
                }
                if (ValidateUtils.isNotBlank(statusReaderTF.getText())) {
                    readerID = Integer.parseInt(statusReaderTF.getText());
                }
                loanSlipList = loanSlipService.findByBookIDAndReaderID(bookID, readerID);
                this.statusBookTB.setItems(FXCollections.observableList(loanSlipList));
            } catch (NumberFormatException e) {
                MessageBoxUtils.AlertBox("Error", "Bạn cần phải nhập số", AlertType.ERROR);
            }
        }
    }

    //Xử lý nút tìm kiếm loadslip trong trang return
    @FXML
    public void loadLoanslipId() {
        String strId = returnLoanslipTF.getText();
        System.out.print(strId);
        if (!"".equals(strId)) {
            try {
                Integer id = Integer.valueOf(strId);
                LoanSlipModel loanSlip = loanSlipService.findById(id);
                if (loanSlip != null) {
                    List<LoanSlipModel> loanSlipList = new ArrayList<>();
                    loanSlipList.add(loanSlip);
                    this.returnLoanslipTB.setItems(FXCollections.observableList(loanSlipList));
                } else {
                    MessageBoxUtils.AlertBox("Error", "Không tìm thấy phiếu mượn", AlertType.ERROR);
                }

            } catch (NumberFormatException e) {
                MessageBoxUtils.AlertBox("Error", "Bạn cần phải nhập số", AlertType.ERROR);
            }
        } else if ("".equals(strId)) {
            MessageBoxUtils.AlertBox("Error", "Bạn chưa nhập id", AlertType.ERROR);
        }
    }

    // xử lý nút tìm kiếm reader trang borrow
    @FXML
    public void loadReaderId() {
        String strId = TFReaderId.getText();
        if (!"".equals(strId)) {
            try {
                Integer id = Integer.valueOf(strId);
                BorrowCardModel borrowCard = borrowCardService.findBorrowCardByRID(id);
                if (borrowCard != null) {
                    loadReaderInfo(id, tbReader);
                } else if (borrowCard == null) {
                    MessageBoxUtils.AlertBox("Error", "Không tìm thấy khách hàng", AlertType.ERROR);
                }

            } catch (NumberFormatException e) {
                MessageBoxUtils.AlertBox("Error", "Bạn cần phải nhập số", AlertType.ERROR);
            }
        } else if ("".equals(strId)) {
            MessageBoxUtils.AlertBox("Error", "Bạn chưa nhập id", AlertType.ERROR);
        }
    }

    // xử lý nút tìm kiếm reader trang return
    @FXML
    public void loadReturnReaderId() {
        String strId = returnReaderTF.getText();
        if (!"".equals(strId)) {
            try {
                Integer id = Integer.valueOf(strId);
                BorrowCardModel borrowCard = borrowCardService.findBorrowCardByRID(id);
                if (borrowCard != null) {
                    loadReaderInfo(id, returnReaderTB);
                } else if (borrowCard == null) {
                    MessageBoxUtils.AlertBox("Error", "Không tìm thấy khách hàng", AlertType.ERROR);
                }

            } catch (NumberFormatException e) {
                MessageBoxUtils.AlertBox("Error", "Bạn chưa nhập số", AlertType.ERROR);
            }
        } else if ("".equals(strId)) {
            MessageBoxUtils.AlertBox("Error", "Bạn chưa nhập id", AlertType.ERROR);
        }
    }

    //xử lý thêm cột bảng loanslip trong trang return
    @FXML
    private void loadLoanslipColumn(TableView<LoanSlipModel> returnLoanslipTB) {
        LoanSlipUtils.load_loanslip_columns(returnLoanslipTB);
    }

    // xử lý nút tìm kiếm book trong trang borrow
    @FXML
    public void loadBookId() {
        String strId = TFBookId.getText();
        int pass = 0;
        if (!"".equals(strId)) {
            try {
                Integer id = Integer.valueOf(strId);
                Map<String, Object> g = new HashMap<>();
                g.put("id", id);

                List<BookModel> bookList = bookService.findBooks(g, null);

                for (BookModel book : bookList) {

                    if (book != null) {
                        loadBookInfo(g, null);
                        pass++;
                    }
                }
                if (pass == 0) {
                    MessageBoxUtils.AlertBox("Error", "Không tìm thấy sách", AlertType.ERROR);
                }

            } catch (NumberFormatException e) {
                MessageBoxUtils.AlertBox("Error", "Bạn chưa nhập số", AlertType.ERROR);
            }
        } else if ("".equals(strId)) {
            MessageBoxUtils.AlertBox("Error", "Bạn chưa nhập id", AlertType.ERROR);
        }
    }

    // xử lý nút kiểm tra reader bên trang Loanslip
    @FXML
    public void checkReaderID() {

        String sid = LSCustomerID.getText();
        if ("".equals(sid)) {
            MessageBoxUtils.AlertBox("INFORMATION", "Vui lòng nhập id vào", AlertType.INFORMATION);
            LScheckReader = 0;
        } else {
            try {
                Integer id = Integer.valueOf(sid);
                int check = readerService.checkReader(id);
                switch (check) {
                    case 0:
                        MessageBoxUtils.AlertBox("ERROR", "Thẻ thư viện đã hết hạn", Alert.AlertType.ERROR);
                        LScheckReader = 0;
                        break;
                    case 2:
                        MessageBoxUtils.AlertBox("ERROR", "Người dùng chưa tạo thẻ thư viện hoặc không tồn tại", Alert.AlertType.ERROR);
                        LScheckReader = 0;
                        break;
                    case 3:
                        MessageBoxUtils.AlertBox("ERROR", "Người dùng chưa trả sách", Alert.AlertType.ERROR);
                        LScheckReader = 0;
                        break;
                    case 1:
                        List<ReaderModel> readerList = readerService.findAll();
                        for (ReaderModel reader : readerList) {
                            if (Objects.equals(reader.getId(), id)) {
                                MessageBoxUtils.AlertBox("INFORMATION", "Người dùng hợp lệ", Alert.AlertType.INFORMATION);
                            }
                        }
                        LScheckReader = 1;
                        break;
                    default:
                        break;
                }

            } catch (NumberFormatException e) {
                MessageBoxUtils.AlertBox("ERROR", "Vui lòng chỉ nhập số", AlertType.ERROR);
                LScheckReader = 0;
            }
        }

    }

    //xử lý nút kiểm tra book bên trang Loanslip
    @FXML
    public void loadLSBook() {
        BookService bookService = new BookServiceImpl();
        String sid = LSBookID.getText();
        LSBookQuantity.setText("1");

        if ("".equals(sid)) {
            MessageBoxUtils.AlertBox("INFORMATION", "Vui lòng nhập id vào", AlertType.INFORMATION);
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
                MessageBoxUtils.AlertBox("ERROR", "Vui lòng chỉ nhập số", AlertType.ERROR);
                LScheckBook = 0;
            }
        }
    }

    //xử lý nút thêm vào danh sách phiếu mượn trong trang Loanslip
    @FXML
    public void LSCreateBookList() {

        if (LScheckBook > 0 && LScheckReader > 0) {
            String strbookid = LSBookID.getText();
            Integer bookid = Integer.valueOf(strbookid);
            String name = LSBookName.getText();
            String author = LSBookAuthor.getText();
            String strquantity = LSBookQuantity.getText();
            Integer quantity = Integer.valueOf(strquantity);

            if (quantity <= 5 && quantity > 0) {

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
                    MessageBoxUtils.AlertBox("INFORMATION", "Đã thêm sách vào danh sách phiếu mượn", AlertType.INFORMATION);
                } else {
                    countQuantity -= quantity;
                    MessageBoxUtils.AlertBox("ERROR", "Tổng số lượng mượn đã lớn hơn 5", AlertType.ERROR);
                }
            } else {
                MessageBoxUtils.AlertBox("ERROR", "Số lượng không được lớn hơn 5 hoặc bé hơn 1", AlertType.ERROR);
            }

        } else {
            MessageBoxUtils.AlertBox("ERROR", "Vui lòng kiểm tra sách và khách hàng trước khi thêm", AlertType.ERROR);
        }
        LScheckBook = 0;
    }

    // xử lý cột trong trang loanslip
    public void loadLSBookListColumn() {
        TableColumn<BookModel, Integer> bookIdColumn = new TableColumn<>("Book ID");
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<BookModel, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<BookModel, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<BookModel, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<BookModel, Boolean> deleteColumn = new TableColumn<>("Delete");
        deleteColumn.setCellValueFactory(cellData -> new SimpleBooleanProperty(true));
        deleteColumn.setCellFactory(column -> {
            return new TableCell<BookModel, Boolean>() {
                private final Button deleteButton = new Button("Delete");

                {
                    deleteButton.setOnAction(event -> {
                        BookModel book = getTableView().getItems().get(getIndex());
                        int currentQuantity = book.getQuantity();
                        if (currentQuantity > 1) {
                            book.setQuantity(currentQuantity - 1);
                            getTableView().refresh();
                            countQuantity--;
                        } else {
                            countQuantity--;
                            getTableView().getItems().remove(book);
                            LSbookList.remove(book);
                        }
                    });
                }

                @Override
                protected void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        setGraphic(deleteButton);
                    } else {
                        setGraphic(null);
                    }
                }
            };
        });

        LSTBBookList.getColumns().addAll(bookIdColumn, nameColumn, authorColumn, quantityColumn, deleteColumn);
    }

    // xử lý nút mở trang danh sách phiếu mượn trong trang Loanslip
    @FXML
    public void loadLSBookListInfo() {
        ObservableList<BookModel> bookObservableList = FXCollections.observableArrayList();
        bookObservableList.addAll(LSbookList);
        LSTBBookList.setItems(bookObservableList);
    }

    //xử lý gán cứng ngày mượn trong trang loanslip
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

    //xử lý nút tạo phiếu mượn
    @FXML
    public void creatLoanSlip() {
        String strId = LSCustomerID.getText();
        Integer rs = loanSlipService.creatLoanSlip(LSbookList, LScheckReader, strId, 1);
        if (rs == 1) {
            MessageBoxUtils.AlertBox("INFORMATION", "Thêm thành công", Alert.AlertType.INFORMATION);
        } else if (rs == 2) {
            MessageBoxUtils.AlertBox("ERROR", "Thư viện không đủ sách", Alert.AlertType.ERROR);
        } else if (rs == 3) {
            MessageBoxUtils.AlertBox("ERROR", "Khách hàng không tồn tại hoặc bạn chưa thêm sách để tạo phiếu mượn", Alert.AlertType.ERROR);
        } else {
            MessageBoxUtils.AlertBox("ERROR", "Thêm thất bại", Alert.AlertType.ERROR);
        }
        clearArray();
    }

    @FXML
    public void clearArray() {
        LSbookList.clear();
        countQuantity = 0;
    }

    @FXML
    public void switchForm(ActionEvent event) {
        String style = "-fx-background-color: #93773e; -fx-text-fill: #fff; -fx-font-weight: bold; -fx-font-style: italic;";
        if (event.getSource() == borrowBook_Btn) {
            loadReaderInfo(null, tbReader);
            loadBookInfo(null, null);
            AnchorPane[] allForms = {searchBook_viewForm, status_viewForm, returnBook_viewForm, readerStatus_viewForm, loanslip_viewForm, bookList_viewForm};
            Button[] allButtons = {searchBook_Btn, status_Btn, returnBook_Btn, reader_Btn};
            borrowBook_viewForm.setVisible(true);
            for (AnchorPane allForm : allForms) {
                allForm.setVisible(false);
            }
            for (Button allButton : allButtons) {
                allButton.setStyle("");
            }
            borrowBook_Btn.setStyle(style);
        }
        if (event.getSource() == searchBook_Btn) {
            searchBook_viewForm.setVisible(true);
            searchBook_Btn.setStyle(style);
            AnchorPane[] allForms = {borrowBook_viewForm, status_viewForm, returnBook_viewForm, readerStatus_viewForm, loanslip_viewForm, bookList_viewForm};
            Button[] allButtons = {borrowBook_Btn, status_Btn, returnBook_Btn, reader_Btn};
            for (AnchorPane allForm : allForms) {
                allForm.setVisible(false);
            }
            for (Button allButton : allButtons) {
                allButton.setStyle("");
            }
        }
        if (event.getSource() == status_Btn) {
            loadLoanslipInfo(statusBookTB);
            status_viewForm.setVisible(true);
            status_Btn.setStyle(style);
            AnchorPane[] allForms = {borrowBook_viewForm, searchBook_viewForm, returnBook_viewForm, readerStatus_viewForm, loanslip_viewForm, bookList_viewForm};
            Button[] allButtons = {borrowBook_Btn, searchBook_Btn, returnBook_Btn, reader_Btn};
            for (AnchorPane allForm : allForms) {
                allForm.setVisible(false);
            }
            for (Button allButton : allButtons) {
                allButton.setStyle("");
            }
        }
        if (event.getSource() == createLoanslipBtn) {
            loanslip_viewForm.setVisible(true);
            AnchorPane[] allForms = {borrowBook_viewForm, searchBook_viewForm, returnBook_viewForm, readerStatus_viewForm, status_viewForm, bookList_viewForm};
            for (AnchorPane allForm : allForms) {
                allForm.setVisible(false);
            }
        }
        if (event.getSource() == returnBook_Btn) {
            loadReaderInfo(null, returnReaderTB);
            loadLoanslipInfo(returnLoanslipTB);
            returnBook_viewForm.setVisible(true);
            returnBook_Btn.setStyle(style);
            AnchorPane[] allForms = {borrowBook_viewForm, searchBook_viewForm, status_viewForm, readerStatus_viewForm, loanslip_viewForm, bookList_viewForm};
            Button[] allButtons = {borrowBook_Btn, searchBook_Btn, status_Btn, reader_Btn};
            for (AnchorPane allForm : allForms) {
                allForm.setVisible(false);
            }
            for (Button allButton : allButtons) {
                allButton.setStyle("");
            }
        }
        if (event.getSource() == reader_Btn) {
            loadReaderStatusInfo();
            readerStatus_viewForm.setVisible(true);
            reader_Btn.setStyle(style);
            AnchorPane[] allForms = {borrowBook_viewForm, searchBook_viewForm, status_viewForm, returnBook_viewForm, loanslip_viewForm, bookList_viewForm};
            Button[] allButtons = {borrowBook_Btn, searchBook_Btn, status_Btn, returnBook_Btn};
            for (AnchorPane allForm : allForms) {
                allForm.setVisible(false);
            }
            for (Button allButton : allButtons) {
                allButton.setStyle("");
            }
        }
        if (event.getSource() == loanslip_exitBtn) {
            borrowBook_viewForm.setVisible(true);
            AnchorPane[] allForms = {loanslip_viewForm, searchBook_viewForm, returnBook_viewForm, readerStatus_viewForm, status_viewForm, bookList_viewForm};
            for (AnchorPane allForm : allForms) {
                allForm.setVisible(false);
            }
        }
        if (event.getSource() == LsBookList) {
            bookList_viewForm.setVisible(true);
            AnchorPane[] allForms = {loanslip_viewForm, searchBook_viewForm, returnBook_viewForm, readerStatus_viewForm, status_viewForm, borrowBook_viewForm};
            for (AnchorPane allForm : allForms) {
                allForm.setVisible(false);
            }
        }
        if (event.getSource() == exitBookListBtn) {
            loanslip_viewForm.setVisible(true);
            AnchorPane[] allForms = {bookList_viewForm, searchBook_viewForm, returnBook_viewForm, readerStatus_viewForm, status_viewForm, borrowBook_viewForm};
            for (AnchorPane allForm : allForms) {
                allForm.setVisible(false);
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bookService = new BookServiceImpl();
        readerService = new ReaderServiceImpl();
        borrowCardService = new BorrowCardServiceImpl();
        loadLSDate();
        loadLSBookListColumn();
        loadReaderColumn(tbReader);
        loadReaderColumn(returnReaderTB);
        loadCate();
        loadSearchBookColumn(tb_SearchBook);
        loadSearchBookInfo(null, null);
        loadLoanslipColumn(returnLoanslipTB);
        loadLoanslipColumn(statusBookTB);
        loadReaderStatusColumn();
        loadBookColumn(tbBook);
        searchBook_Btn.requestFocus();
        searchBook_Btn.fire();
    }

}
