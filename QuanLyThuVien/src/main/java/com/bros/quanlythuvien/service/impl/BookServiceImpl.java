/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.service.impl;

import com.bros.quanlythuvien.converter.BookConverter;
import com.bros.quanlythuvien.entity.BookEntity;
import com.bros.quanlythuvien.entity.CategoryEntity;
import com.bros.quanlythuvien.model.BookModel;
import com.bros.quanlythuvien.model.CategoryModel;
import com.bros.quanlythuvien.model.SearchBookModel;
import com.bros.quanlythuvien.repository.BookRepository;
import com.bros.quanlythuvien.repository.CategoryRepository;
import com.bros.quanlythuvien.repository.impl.BookRepositoryImpl;
import com.bros.quanlythuvien.repository.impl.CategoryRepositoryImpl;
import com.bros.quanlythuvien.service.BookService;
import com.bros.quanlythuvien.service.CategoryService;
import com.bros.quanlythuvien.utils.ValidateUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 *
 * @author Dinh Chuong
 */
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository = new BookRepositoryImpl();
    private BookConverter bookConverter = new BookConverter();
    private CategoryRepository categoryRepository = new CategoryRepositoryImpl();

    @Override
    public List<BookModel> findAll(Integer page) {
        List<BookEntity> bookList = bookRepository.findAll(null);
        List<BookModel> resultsBookModel = new ArrayList<>();

        for (BookEntity entity : bookList) {
            resultsBookModel.add(bookConverter.entityToModel(entity, BookModel.class));
        }
        return resultsBookModel;
    }

    @Override
    public BookModel findById(Integer id) {
        return null;
    }

    @Override
    public List<BookModel> findBooks(Map<String, Object> searchMap, Integer page) {

        List<BookEntity> resultsBookEntity = bookRepository.findBooks(searchMap, page);

        List<BookModel> resultsBookModel = new ArrayList<>();

        for (BookEntity entity : resultsBookEntity) {
            CategoryEntity category = categoryRepository.findById(entity.getCategoryID());
            BookModel bookModel = bookConverter.entityToModel(category, entity, BookModel.class);
            resultsBookModel.add(bookModel);
        }
        return resultsBookModel;
    }

    @Override
    public Map<String, Object> getSearchMap(String strTitle, String strAuthor, Integer cateID, String strPublish) {
        Map<String, Object> searchMap = new HashMap<>();
        if (ValidateUtils.isValid(strTitle)) {
            searchMap.put("BookTitle", strTitle);
        }
        if (ValidateUtils.isValid(strAuthor)) {
            searchMap.put("Author", strAuthor);
        }
        if (ValidateUtils.isValid(cateID)) {
            searchMap.put("CategoryID", cateID);
        }
        if (ValidateUtils.isValid(strPublish)) {
            try {
                searchMap.put("PublicationYear", Integer.valueOf(strPublish));
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("ERROR");
                alert.setContentText("Vui lòng nhập số");
                alert.showAndWait();
            }
        }
        return searchMap;
    }

    @Override
    public BookModel getBook(TextField id, TextField title, TextField author, TextField description,
            TextField publicationPlace, TextField publicationYear, ComboBox<String> category, TextField location, TextField quantity, Map<Integer, String> catemap) {
        BookModel book = new BookModel();
        if ("".equals(id.getText())) {
            return book = null;
        }
        if ("".equals(title.getText()) && "".equals(author.getText())) {
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
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("ERROR");
                alert.setContentText("Vui lòng nhập số");
                alert.showAndWait();
            }
        }

        book.setPublicationPlace(publicationPlace.getText());
        if (!"".equals(quantity.getText())) {
            try {
                book.setQuantity(Integer.valueOf(quantity.getText()));
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("ERROR");
                alert.setContentText("Vui lòng nhập số");
                alert.showAndWait();
            }
        }

        String selectedCategory = category.getValue();
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

    @Override
    public void updateBook(BookModel book) {
        boolean rs = bookRepository.updateBook(book);
        if (rs) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Success");
            alert.setContentText("Sửa đổi dữ liệu thành công");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("ERROR");
            alert.setContentText("Sửa đổi dữ liệu thất bại");
            alert.showAndWait();
        }
    }

    @Override
    public void inserBook(BookModel book) {
        boolean rs = bookRepository.insertBook(book);
        if (rs) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Success");
            alert.setContentText("Thêm dữ liệu thành công");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("ERROR");
            alert.setContentText("Thêm dữ liệu thất bại");
            alert.showAndWait();
        }
    }

    @Override
    public void deleteBook(Integer id) {
        boolean rs = bookRepository.deleteBook(id);
        if (rs) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Success");
            alert.setContentText("Xóa dữ liệu thành công");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("ERROR");
            alert.setContentText("Xóa dữ liệu thất bại");
            alert.showAndWait();
        }
    }
}
