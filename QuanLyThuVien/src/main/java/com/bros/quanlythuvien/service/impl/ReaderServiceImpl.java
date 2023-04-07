/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.service.impl;

import com.bros.quanlythuvien.converter.ReaderConverter;
import com.bros.quanlythuvien.entity.ReaderEntity;
import com.bros.quanlythuvien.model.BookModel;
import com.bros.quanlythuvien.model.ReaderModel;
import com.bros.quanlythuvien.repository.ReaderRepository;
import com.bros.quanlythuvien.repository.impl.ReaderRepositoryImpl;
import com.bros.quanlythuvien.service.ReaderService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author phu nguyen
 */
public class ReaderServiceImpl implements ReaderService {

    ReaderRepository readerRepository = new ReaderRepositoryImpl();
    ReaderConverter readerConverter = new ReaderConverter();

    @Override
    public ReaderModel findById(Integer id) {
        ReaderEntity readerEntity = readerRepository.findById(id);
        return readerConverter.entityToModel(readerEntity, ReaderModel.class);
    }

    @Override
    public List<ReaderModel> findAll() {
        List<ReaderEntity> borrowCardList = readerRepository.findAll(null);
        List<ReaderModel> resultsBorrowCardModel = new ArrayList<>();
        for (ReaderEntity entity : borrowCardList) {
            resultsBorrowCardModel.add(readerConverter.entityToModel(entity, ReaderModel.class));
        }
        return resultsBorrowCardModel;
    }

    @Override
    public void loadReaderColumn(TableView<ReaderModel> infoCustomerTB) {
        TableColumn colId = new TableColumn("ReaderId");
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn colName = new TableColumn("Fullname");
        colName.setCellValueFactory(new PropertyValueFactory("fullname"));
        TableColumn colGender = new TableColumn("Gender");
        colGender.setCellValueFactory(new PropertyValueFactory("gender"));
        TableColumn colDateOfBirth = new TableColumn("BirthDay");
        colDateOfBirth.setCellValueFactory(new PropertyValueFactory("dateOfBirth"));

        infoCustomerTB.getColumns().addAll(colId, colName, colGender, colDateOfBirth);
    }

    @Override
    public void loadSearchBookColumn(TableView<BookModel> TBRSearchBook, List<BookModel> bookListCart) {
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

    @Override
    public void loadCartColumn(TableView<BookModel> tb_Cart, List<BookModel> bookListCart) {
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

    @Override
    public void loadInfoCart(List<BookModel> bookListCart, TableView<BookModel> tb_Cart, Integer page) {
        tb_Cart.setItems(FXCollections.observableList(bookListCart));
        tb_Cart.refresh();
    }

    @Override
    public void loadCate(ComboBox<String> RsearchBook_category, Map<Integer, String> categoriesMap) {
        readerRepository.loadCate(RsearchBook_category, categoriesMap);
    }

    @Override
    public int checkReader(Integer id) {
        switch (readerRepository.checkReader(id)) {
            case 0: {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("ERROR");
                alert.setContentText("Thẻ thư viện đã hết hạn");
                alert.showAndWait();
                return 0;
            }
            case 1:
                List<ReaderModel> readerList = findAll();
                for (ReaderModel reader : readerList) {
                    if (Objects.equals(reader.getId(), id)) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setHeaderText("Success");
                        alert.setContentText("Người dùng hợp lệ");
                        alert.showAndWait();
                    }
                }
                return 1;
            case 2: {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Người dùng không tồn tại");
                alert.showAndWait();
                return 0;
            }
            case 3: {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("ERROR");
                alert.setContentText("Người dùng chưa trả sách");
                alert.showAndWait();
                return 0;
            }
            default:
                break;
        }
        return 0;
    }

    @Override
    public Map<String, Object> login(TextField username, TextField password, Button loginBtn) {
        Map<String, Object> resultMap = readerRepository.login(username, password, loginBtn);
        return resultMap;
    }

    @Override
    public int register(TextField register_username, TextField register_password, TextField register_fullname, TextField register_email) {
        int rs = readerRepository.register(register_username, register_password, register_fullname, register_email);
        return rs;
    }

    @Override
    @FXML
    public void InforReader(TableView<ReaderModel> infoCustomerTB, TextField infomation_name, ComboBox<String> infomation_gender, DatePicker infomation_birthDay) {
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

    @Override
    @FXML
    public void InforReaderAdmin(TableView<ReaderModel> tbReader, TextField customer_id, TextField customer_name, ComboBox<String> customer_gender, DatePicker customer_birthDay) {
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

    @Override
    public void loadGender(ComboBox<String> infomation_gender) {
        infomation_gender.setPromptText("Chọn giới tính");
        infomation_gender.getItems().addAll("Chọn giới tính", "Nam", "Nữ");
    }

    @Override
    public boolean updateReader(ReaderModel reader) {
        boolean rs = readerRepository.updateReader(reader);
        return rs;
    }

    @Override
    public ReaderModel createReaderModel(Integer readerId, TextField infomation_name, ComboBox<String> infomation_gender, DatePicker infomation_birthDay) {
        ReaderModel reader = new ReaderModel();
        reader.setId(readerId);
        reader.setFullname(infomation_name.getText());
        String gender = infomation_gender.getValue();
        if ("Nam".equals(gender) || "Nữ".equals(gender)) {
            reader.setGender(gender);
        } else {
            reader.setGender("");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedBirthDay = infomation_birthDay.getValue().format(formatter);
        reader.setDateOfBirth(formattedBirthDay);
//        reader.setFullname(infomation_birthDay.toString());
        return reader;
    }
}
