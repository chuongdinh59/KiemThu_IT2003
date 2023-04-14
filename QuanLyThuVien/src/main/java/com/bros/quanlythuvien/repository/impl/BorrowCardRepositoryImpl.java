/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.repository.impl;

import com.bros.quanlythuvien.entity.BorrowCardEntity;
import com.bros.quanlythuvien.repository.BorrowCardRepository;
import static com.bros.quanlythuvien.utils.ConnectionUtils.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class BorrowCardRepositoryImpl extends CommonRepositoryImpl<BorrowCardEntity> implements BorrowCardRepository {

    @Override
    public List<Integer> findreaderId() {
        Connection connect = getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        List<Integer> myList = new ArrayList<>();
        try {
            String sql = "SELECT ReaderID FROM borrowcards WHERE ExpiryDate > NOW();";
            statement = connect.prepareStatement(sql);
            result = statement.executeQuery();
            while (result.next()) {
                int readerID = result.getInt("ReaderID");
                myList.add(readerID);
            }
        } catch (SQLException e) {
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
        return myList;
    }

    @Override
    public BorrowCardEntity findBorrowCardByRID(int ReaderID) {
        BorrowCardEntity b = super.findByRId(ReaderID);
        if (b == null) {
            return null;
        }
        return b;
    }

    @Override
    public List<BorrowCardEntity> findAll(Integer page) {
        List<BorrowCardEntity> b = super.findAll();
        return b;
    }

    @Override
    public boolean createBorrowCard(Integer id) {
        PreparedStatement statement = null;
        Connection connect = getConnection();
        BorrowCardEntity b = findByRId(id);
        if (b != null) {
            try {
                String sql = "DELETE FROM borrowcards WHERE (ReaderID = ?);";
                statement = connect.prepareStatement(sql);
                statement.setInt(1, id);
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    try {
                        String sql2 = "INSERT INTO borrowcards (ReaderID,IssuedDate,ExpiryDate) VALUES(?,?,?)";
                        statement = connect.prepareStatement(sql2);
                        statement.setInt(1, id);
                        // Lấy ngày hôm nay
                        LocalDate today = LocalDate.now();

// Thêm 1 năm vào ngày hôm nay để tính ExpiryDate
                        LocalDate expiryDate = today.plusYears(1);
                        statement.setString(2, today.toString());
                        statement.setString(3, expiryDate.toString());
                        int rowsUpdated2 = statement.executeUpdate();
                        if (rowsUpdated2 > 0) {
                            return true;
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    } finally {
                        try {
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                String sql = "INSERT INTO borrowcards (ReaderID,IssuedDate,ExpiryDate) VALUES(?,?,?)";
                statement = connect.prepareStatement(sql);
                statement.setInt(1, id);
                // Lấy ngày hôm nay
                LocalDate today = LocalDate.now();

// Thêm 1 năm vào ngày hôm nay để tính ExpiryDate
                LocalDate expiryDate = today.plusYears(1);
                statement.setString(2, today.toString());
                statement.setString(3, expiryDate.toString());
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally {
                try {
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
        return false;
    }

//    public static void main(String[] args) {
//        BorrowCardRepositoryImpl borrowCardRepository = new BorrowCardRepositoryImpl();
//        BorrowCardEntity borrowCard = borrowCardRepository.findBorrowCardByRID(3);
//        System.out.println("ID: " + borrowCard.getId());
//        System.out.println("Reader ID: " + borrowCard.getReaderID());
//        System.out.println("Issued Date: " + borrowCard.getIssuedDate());
//        System.out.println("Expiry Date: " + borrowCard.getExpiredDate());
//
//    }
}
