/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.service.impl;

import com.bros.quanlythuvien.EmployeeController;
import com.bros.quanlythuvien.converter.ReaderConverter;
import com.bros.quanlythuvien.entity.ReaderEntity;
import com.bros.quanlythuvien.model.BookModel;
import com.bros.quanlythuvien.model.ReaderModel;
import com.bros.quanlythuvien.repository.ReaderRepository;
import com.bros.quanlythuvien.repository.impl.ReaderRepositoryImpl;
import com.bros.quanlythuvien.service.BookService;
import com.bros.quanlythuvien.service.ReaderService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

 
    
}
