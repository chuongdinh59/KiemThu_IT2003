/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.repository.impl;

import com.bros.quanlythuvien.entity.ReaderEntity;
import com.bros.quanlythuvien.model.ReaderModel;
import com.bros.quanlythuvien.repository.ReaderRepository;
import static com.bros.quanlythuvien.utils.ConnectionUtils.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

/**
 *
 * @author phu nguyen
 */
public class ReaderRepositoryImpl extends CommonRepositoryImpl<ReaderEntity> implements ReaderRepository {

    private PreparedStatement statement;
    private ResultSet result;

    @Override
    public ReaderEntity findReaderById(Integer readerId) {
        ReaderEntity r = super.findById(readerId);
        return r;
    }

    @Override
    public List<ReaderEntity> findAll(Integer page) {
        List<ReaderEntity> r = super.findAll();
        return r;
    }

    @Override
    public void loadCate(ComboBox<String> RsearchBook_category, Map<Integer, String> categoriesMap) {
        Connection connect = getConnection();
        RsearchBook_category.setPromptText("Chọn thể loại");
        RsearchBook_category.getItems().add(0, "Chọn thể loại");
        categoriesMap.clear();
        try {
            String sql = "select * from category;";
            statement = connect.prepareStatement(sql);
            result = statement.executeQuery();
            while (result.next()) {
                Integer id = result.getInt("id");
                String value = result.getString("value");
                categoriesMap.put(id, value);
            }
            RsearchBook_category.getItems().addAll(categoriesMap.values());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int checkReader(Integer id) {
        Connection connect = getConnection();
        int fail = 0;
        try {
            String sql = "select ExpiryDate from borrowcards where ReaderID =?;";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, id);
            result = statement.executeQuery();
            if (result.next()) {

                LocalDate expiryDate = result.getDate("ExpiryDate").toLocalDate(); // Lấy giá trị ExpiryDate từ ResultSet

                LocalDate currentDate = LocalDate.now(); // Lấy ngày hiện tại

                if (expiryDate.isBefore(currentDate)) {
                    return 0;
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
                                return 3;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                        return 1;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 2;
    }

//    public static void main(String[] args) {
//        ReaderRepositoryImpl readerRepository = new ReaderRepositoryImpl();
//        List<ReaderEntity> readerList = readerRepository.findAll();
////        ReaderEntity reader = readerRepository.findReaderById(1, null);
//
//        for (ReaderEntity reader : readerList) {
//            System.out.println("Reader ID: " + reader.getId());
//            System.out.println("Reader Name: " + reader.getFullName());
//            System.out.println("Reader Address: " + reader.getGender());
//            System.out.println("Reader Email: " + reader.getDateOfBirth());
//            System.out.println("Reader Email: " + reader.getReaderType());
//
//        }
//
//    }
}
