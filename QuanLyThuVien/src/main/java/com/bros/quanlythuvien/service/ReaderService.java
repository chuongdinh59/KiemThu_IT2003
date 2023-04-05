/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.service;

import com.bros.quanlythuvien.model.BookModel;
import com.bros.quanlythuvien.model.ReaderModel;
import java.util.List;
import java.util.Map;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

/**
 *
 * @author phu nguyen
 */
public interface ReaderService {

    public ReaderModel findById(Integer id);

    public List<ReaderModel> findAll();

    public void loadSearchBookColumn(TableView<BookModel> TBRSearchBook, List<BookModel> bookListCart);

    public void loadCate(ComboBox<String> RsearchBook_category, Map<Integer, String> categoriesMap);

    public void loadCartColumn(TableView<BookModel> tb_Cart, List<BookModel> bookListCart);

    public void loadInfoCart(List<BookModel> bookListCart, TableView<BookModel> tb_Cart, Integer page);

    public int checkReader(Integer id);


    public void loadReaderColumn(TableView<ReaderModel> infoCustomerTB);

}
