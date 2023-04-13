/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien;

import com.bros.quanlythuvien.model.BookModel;
import com.bros.quanlythuvien.model.CategoryModel;
import com.bros.quanlythuvien.model.ReaderModel;
import com.bros.quanlythuvien.model.ReportModel;
import com.bros.quanlythuvien.service.BookService;
import com.bros.quanlythuvien.service.CategoryService;
import com.bros.quanlythuvien.service.LoanSlipService;
import com.bros.quanlythuvien.service.ReaderService;
import com.bros.quanlythuvien.service.impl.BookServiceImpl;
import com.bros.quanlythuvien.service.impl.CategoryServiceImpl;
import com.bros.quanlythuvien.service.impl.LoanSlipServiceImpl;
import com.bros.quanlythuvien.service.impl.ReaderServiceImpl;
import com.bros.quanlythuvien.utils.MessageBoxUtils;
import com.bros.quanlythuvien.utils.ReaderUtils;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
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

    @FXML
    private TextField customer_name;

    @FXML
    private DatePicker customer_birthDay;

    @FXML
    private ComboBox<String> customer_gender;

    @FXML
    private Button customer_updateBtn;
    @FXML
    private TextField customer_id;

    @FXML
    private Button report_Btn;
    @FXML
    private AnchorPane report_viewForm;
    @FXML
    private ComboBox<String> ComboBoxYear;

    @FXML
    private LineChart<String, Number> YearChart;

    BookService bookService;
    private Map<Integer, String> categoriesMap = new HashMap<>();
    private ReaderService readerService = new ReaderServiceImpl();
    private CategoryService categoryService = new CategoryServiceImpl();

//    Hàm
    public BookModel get_book(TextField id, TextField title, TextField author, TextField description,
            TextField publicationPlace, TextField publicationYear, ComboBox<String> category, TextField location, TextField quantity, Map<Integer, String> catemap) {
        BookModel book = new BookModel();
        if ("".equals(id.getText())) {
            return book = null;
        }
        if ("".equals(title.getText()) || "".equals(author.getText()) || "".equals(description.getText())
                || "".equals(publicationPlace.getText()) || "".equals(location.getText())) {
            return book = null;
        }
        book.setId(Integer.valueOf(id.getText()));
        book.setTitle(title.getText());
        book.setAuthor(author.getText());
        book.setDescription(description.getText());
        if (!"".equals(publicationYear.getText())) {
            try {
                book.setPublicationYear(Integer.valueOf(publicationYear.getText()));
            } catch (NumberFormatException e) {
                MessageBoxUtils.AlertBox("ERROR", "Vui lòng nhập số", AlertType.ERROR);
            }
        } else {
            return book = null;
        }

        book.setPublicationPlace(publicationPlace.getText());
        if (!"".equals(quantity.getText())) {
            try {
                book.setQuantity(Integer.valueOf(quantity.getText()));
            } catch (NumberFormatException e) {
                MessageBoxUtils.AlertBox("ERROR", "Vui lòng nhập số", AlertType.ERROR);
            }
        } else {
            return book = null;
        }

        int a = 1;
        String selectedCategory = category.getValue();
        System.out.println(selectedCategory);
        if (selectedCategory == null || selectedCategory.equals("Chọn thể loại")) {

            return book = null;
        }
        Integer cateID = null;
        for (Map.Entry<Integer, String> entry : catemap.entrySet()) {
            if (entry.getValue().equals(selectedCategory)) {
                cateID = entry.getKey();
                break;
            }
        }
        book.setCategoryID(cateID);
        book.setLocation(location.getText());
        return book;
    }

    public void load_cate(ComboBox<String> availableBooks_category, Map<Integer, String> categoriesMap, List<CategoryModel> categories) {
        availableBooks_category.setPromptText("Chọn thể loại");
        availableBooks_category.getItems().add(0, "Chọn thể loại");
        categoriesMap.clear();

        try {
            for (CategoryModel c : categories) {
                categoriesMap.put(c.getCategoryID(), c.getValue());
            }
            availableBooks_category.getItems().addAll(categoriesMap.values());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void info_reader_admin(TableView<ReaderModel> tbReader, TextField customer_id, TextField customer_name, ComboBox<String> customer_gender, DatePicker customer_birthDay) {
        ReaderModel rowData = tbReader.getSelectionModel().getSelectedItem();
        if (rowData != null) {
            customer_id.setText(rowData.getId().toString());
            customer_name.setText(rowData.getFullname());
//            String gender = rowData.getGender();
            if (rowData.getGender() == null) {
                customer_gender.setPromptText("Chọn giới tính");
            } else if (rowData.getGender().equals("Nam") || rowData.getGender().equals("Nữ")) {
                customer_gender.setValue(rowData.getGender());
            } else {
                customer_gender.setPromptText("Chọn giới tính");
            }
            if (rowData.getDateOfBirth() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate birthDay = LocalDate.parse(rowData.getDateOfBirth(), formatter);
                customer_birthDay.setValue(birthDay);
            }
        }
    }

    public void clear_add_book(TextField addBook_title, TextField addBook_author, TextField addBook_description, TextField addBook_publishedPlace, TextField addBook_publishedYear, ComboBox<String> addBook_category, TextField addBook_location, TextField addBook_quantity) {
        addBook_title.setText("");
        addBook_author.setText("");
        addBook_description.setText("");
        addBook_publishedPlace.setText("");
        addBook_publishedYear.setText("");
        addBook_category.setValue("Chọn thể loại");
        addBook_location.setText("");
        addBook_quantity.setText("");
    }
//    ----------------------------------------

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
            report_viewForm.setVisible(false);

        }
        if (event.getSource() == customer_btn) {
            loadReaderInfo();
            availableBooks_viewForm.setVisible(false);
            customer_viewForm.setVisible(true);
            report_viewForm.setVisible(false);
            add_viewForm.setVisible(false);

        }
        if (event.getSource() == availableBooks_addBtn) {
            availableBooks_viewForm.setVisible(false);
            add_viewForm.setVisible(true);
            report_viewForm.setVisible(false);
            customer_viewForm.setVisible(false);

        }
        if (event.getSource() == addBook_exitBtn) {
            availableBooks_viewForm.setVisible(true);
            report_viewForm.setVisible(false);
            customer_viewForm.setVisible(false);

            add_viewForm.setVisible(false);
        }
        if (event.getSource() == report_Btn) {
            report_viewForm.setVisible(true);
            availableBooks_viewForm.setVisible(false);
            add_viewForm.setVisible(false);
            customer_viewForm.setVisible(false);
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
//        this.categoryService = new CategoryServiceImpl();
        loadGender();
        loadReaderColumn();
        loadBookColumn();
        loadBookInfo(null, null);
        loadCateAvailable();
        loadCateAdd();
        clear();

//        ----------------------------------
        loanSlipService = new LoanSlipServiceImpl();
        reportBorrowModels = loanSlipService.getReportBorrow();
        reportReturnModels = loanSlipService.getReportReturn();
        load();
        loadComboBox();

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
        ReaderUtils.load_reader_columns(tbReader);
    }

    //hiển thị dữ liệu bảng reader
    @FXML
    private void loadReaderInfo() {
        List<ReaderModel> readerList = readerService.findAll();
        this.tbReader.setItems(FXCollections.observableList(readerList));
    }

    @FXML
    private void loadCateAvailable() {
        List<CategoryModel> categories = categoryService.findAll();
        load_cate(availableBooks_category, categoriesMap, categories);
    }

    @FXML
    private void loadCateAdd() {
        List<CategoryModel> categories = categoryService.findAll();
        load_cate(addBook_category, categoriesMap, categories);
    }

    @FXML
    private void updateBook() {
        BookModel book = get_book(availableBooks_bookID, availableBooks_title,
                availableBooks_author, availableBooks_description, availableBooks_publishedPlace,
                availableBooks_publishedYear, availableBooks_category, availableBooks_location, availableBooks_quantity, categoriesMap);
        bookService.updateBook(book);
        loadBookInfo(null, null);
        clear();

    }

    @FXML
    private void insertBook() {

        availableBooks_bookID.setText("0");

        BookModel book = get_book(availableBooks_bookID, addBook_title,
                addBook_author, addBook_description, addBook_publishedPlace,
                addBook_publishedYear, addBook_category, addBook_location, addBook_quantity, categoriesMap);
        if (book != null) {
            bookService.inserBook(book);
            clear_add_book(addBook_title, addBook_author, addBook_description, addBook_publishedPlace, addBook_publishedYear, addBook_category, addBook_location, addBook_quantity);
        } else {
            MessageBoxUtils.AlertBox("ERROR", "Sửa đổi dữ liệu thất bại", AlertType.ERROR);
        }
        loadBookInfo(null, null);
        clear();

    }

    @FXML
    private void deleteBook() {
        BookModel book = get_book(availableBooks_bookID, availableBooks_title,
                availableBooks_author, availableBooks_description, availableBooks_publishedPlace,
                availableBooks_publishedYear, availableBooks_category, availableBooks_location, availableBooks_quantity, categoriesMap);
        if (book != null) {
            bookService.deleteBook(book.getId());
        }
        loadBookInfo(null, null);
        clear();

    }

    //Hiển thị combobox gender
    @FXML
    private void loadGender() {
        ReaderUtils.load_gender(customer_gender);
    }

    //nhấn vào table reader xuất ra thông tin tương ứng
    @FXML
    private void InforReader() {
        info_reader_admin(tbReader, customer_id, customer_name, customer_gender, customer_birthDay);
    }

    //Update thông tin reader
    @FXML
    private void updateReader() {
        Integer id = Integer.valueOf(customer_id.getText());
        ReaderModel reader = ReaderUtils.create_readerModel(id, customer_name, customer_gender, customer_birthDay);
        boolean rs = readerService.updateReader(reader);
        if (rs) {
            MessageBoxUtils.AlertBox("INFORMATION", "Sửa đổi thành công", AlertType.INFORMATION);
            loadReaderInfo();
            tbReader.refresh();
        } else {
            MessageBoxUtils.AlertBox("ERROR", "Sửa đổi thất bại", AlertType.ERROR);
        }
    }

//    report----------------------------------------------------------
    private LoanSlipService loanSlipService;
    private List<ReportModel> reportBorrowModels;
    private List<ReportModel> reportReturnModels;

    public void load() {
        // borrow
        Map<Integer, Integer> mapReport = mapReportYearQuantiry(reportBorrowModels);
        XYChart.Series<String, Number> seriesBorrow = new XYChart.Series<>();
        for (Map.Entry<Integer, Integer> entry : mapReport.entrySet()) {
            seriesBorrow.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));

        }
        seriesBorrow.setName("Số sách mượn mỗi năm");
        YearChart.getData().add(seriesBorrow);

        // return chart
        Map<Integer, Integer> mapReturnReport = mapReportYearQuantiry(reportReturnModels);
        XYChart.Series<String, Number> seriesReturn = new XYChart.Series<>();
        for (Map.Entry<Integer, Integer> entry : mapReturnReport.entrySet()) {
            seriesReturn.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
        }
        seriesReturn.setName("Số sách trả mỗi năm");
        YearChart.getData().add(seriesReturn);

    }

    public void loadComboBox() {
        Set<String> years = getYears();
        ObservableList<String> options = FXCollections.observableArrayList(
                years
        );
        // Set default value
        ComboBoxYear.setValue("Chọn năm");
        ComboBoxYear.setItems(options);
        ComboBoxYear.setOnAction(event -> {
            String selectedValue = ComboBoxYear.getValue();
            showPieChartDialog(selectedValue);
        });
    }

//    @FXML
//    public void initialize() {
//        loanSlipService = new LoanSlipServiceImpl();
//        reportBorrowModels = loanSlipService.getReportBorrow();
//        reportReturnModels =  loanSlipService.getReportReturn();
//        load();
//        loadComboBox();
//    }
    private Map<Integer, Integer> mapReportYearQuantiry(List<ReportModel> reportModels) {
        Map<Integer, Integer> result = new HashMap<>();
        for (ReportModel report : reportModels) {
            Integer value = result.getOrDefault(report.getYear(), 0);
            result.put(report.getYear(), value + report.getQuantity());
        }
        return result;
    }

    private Map<String, Integer> mapReportQuaterQuantiry(List<ReportModel> reportModels, Integer targetYear) {
        Map<String, Integer> result = new HashMap<>();
        for (ReportModel report : reportModels) {
            if (Objects.equals(report.getYear(), targetYear)) {
                Integer value = result.getOrDefault(report.getQuarter(), 0);
                result.put(report.getQuarter(), value + report.getQuantity());
            }
        }
        return result;
    }

    private void showPieChartDialog(String selectedValue) {

        Map<String, Integer> reportBorrowMap = mapReportQuaterQuantiry(reportBorrowModels, Integer.valueOf(selectedValue));
        // Create a new Stage for the modal dialog
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Thông kê trong năm " + selectedValue);

        // Create two new PieCharts and populate them with data
        PieChart pieChart1 = new PieChart();
        pieChart1.setTitle("Thống kê số sách mượn theo quý trong năm " + selectedValue);
        for (Map.Entry<String, Integer> entry : reportBorrowMap.entrySet()) {
            PieChart.Data data = new PieChart.Data(entry.getKey(), entry.getValue());
            pieChart1.getData().add(data);
//            Tooltip tooltip = new Tooltip(entry.getKey() + ": " + entry.getValue());
//            System.out.println("Tooltip message: " + tooltip.getText());
//            tooltip.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-color: black;");
//            Tooltip.install(data.getNode(), tooltip); // set tooltip for data node

            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                MessageBoxUtils.AlertBox("INFORMATION", entry.getKey() + " : " + entry.getValue() + " quyển sách", AlertType.INFORMATION);
            });
        }

        Map<String, Integer> reportReturnMap = mapReportQuaterQuantiry(reportReturnModels, Integer.valueOf(selectedValue));
        PieChart pieChart2 = new PieChart();
        pieChart2.setTitle("Thống kê số sách trả theo quý trong năm " + selectedValue);
        for (Map.Entry<String, Integer> entry : reportReturnMap.entrySet()) {
            PieChart.Data data = new PieChart.Data(entry.getKey(), entry.getValue());
            pieChart2.getData().add(data);
//        Tooltip tooltip = new Tooltip(entry.getKey() + ": " + entry.getValue());
//        tooltip.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-color: black;");
//        System.out.println("Tooltip message: " + tooltip.getText());
//        Tooltip.install(data.getNode(), tooltip); // set tooltip for data node

            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                MessageBoxUtils.AlertBox("INFORMATION", entry.getKey() + " : " + entry.getValue() + " quyển sách", AlertType.INFORMATION);
            });
        }

        // Create a new HBox and add the PieCharts to it
        HBox hBox = new HBox();
        hBox.getChildren().addAll(pieChart1, pieChart2);
        // Create a new Scene containing the VBox
        Scene dialogScene = new Scene(new Group(hBox));
        dialog.setScene(dialogScene);
        dialog.show();
    }

    private Set<String> getYears() {
        Set<String> years = new HashSet<>();
        for (ReportModel report : reportBorrowModels) {
            years.add(report.getYear().toString());
        }
        return years;
    }

}
