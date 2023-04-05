/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.repository.impl;

import com.bros.quanlythuvien.entity.LoanSlipEntity;
import com.bros.quanlythuvien.model.BookModel;
import com.bros.quanlythuvien.model.LoanSlipModel;
import static com.bros.quanlythuvien.utils.ConnectionUtils.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import com.bros.quanlythuvien.repository.LoanSlipRepository;

/**
 *
 * @author ADMIN
 */
public class LoanSlipRepositoryImpl extends CommonRepositoryImpl<LoanSlipEntity> implements LoanSlipRepository {

    private PreparedStatement statement;
    private ResultSet result;

    @Override
    public List<LoanSlipEntity> findAll(Integer page) {
        List<LoanSlipEntity> r = super.findAll();
        return r;
    }

    @Override
    public LoanSlipEntity findReaderById(Integer loanslipId) {
        LoanSlipEntity r = super.findById(loanslipId);
        return r;
    }

    @Override
    public boolean updateBook(LoanSlipModel loanSlip) {
        Connection connect = getConnection();
        if (loanSlip == null) {
            return false;
        }

        try {
            String sql = "SELECT * FROM loanslip WHERE id = ?;";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, loanSlip.getId());
            result = statement.executeQuery();
            if (result.next()) {
                LocalDate today = LocalDate.now();
                java.sql.Date sqlDate = java.sql.Date.valueOf(today);
                String date = result.getString("ExpirationDate");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date expirationDate = dateFormat.parse(date);
                java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(expirationDate.getTime());

                long daysBetween = (sqlDate.getTime() - sqlTimestamp.getTime()) / (1000 * 60 * 60 * 24);
                if (daysBetween > 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("INFORMATION");
                    alert.setHeaderText("INFORMATION");
                    alert.setContentText("Bạn đã trễ hạn " + daysBetween + " ngày và tiền phạt là: " + 5000 * daysBetween + " VNĐ");
                    alert.showAndWait();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(LoanSlipRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
//            String sql = "UPDATE loanslip SET isReturned = '1', ExpirationDate = ? WHERE (id = ?);";
            String sql = "UPDATE loanslip SET isReturned = '1' WHERE (id = ?);";

            statement = connect.prepareStatement(sql);
//            // Sử dụng java.util.Date
//            LocalDate today = LocalDate.now();
//            java.sql.Date sqlDate = java.sql.Date.valueOf(today);
//            statement.setDate(1, sqlDate);

            statement.setInt(1, loanSlip.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                insertQuantity(loanSlip.getQuantity(), loanSlip.getBookID());
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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
        return false;
    }

    @Override
    public boolean updateBookGive(LoanSlipModel loanSlip) {
        Connection connect = getConnection();
        if (loanSlip == null) {
            return false;
        }

        try {
            String sql = "UPDATE loanslip SET isOnline = '1'WHERE (id = ?);";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, loanSlip.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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
        return false;
    }

    @Override
    public List<LoanSlipEntity> findByCId(Integer id) {
        Connection connect = getConnection();
        List<LoanSlipEntity> loanSlipList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM loanslip WHERE CustomerID = ?";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, id);
            result = statement.executeQuery();
            while (result.next()) {
                LoanSlipEntity loanSlip = new LoanSlipEntity();
                loanSlip.setId(result.getInt("id"));
                loanSlip.setCustomerID(result.getInt("CustomerID"));
                loanSlip.setBookID(result.getInt("BookID"));
                loanSlip.setBookName(result.getString("BookName"));
                loanSlip.setBookAuthor(result.getString("BookAuthor"));
                loanSlip.setBorrowedDate(result.getDate("BorrowedDate"));
                loanSlip.setExpirationDate(result.getDate("ExpirationDate"));
                loanSlip.setQuantity(result.getInt("Quantity"));
                loanSlip.setIsReturned(result.getInt("isReturned"));
                loanSlip.setIsOnline(result.getInt("isOnline"));
                loanSlipList.add(loanSlip);
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
        return loanSlipList;
    }

    @Override
    public List<LoanSlipEntity> findByBId(Integer id) {
        Connection connect = getConnection();
        List<LoanSlipEntity> loanSlipList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM loanslip WHERE BookID = ?";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, id);
            result = statement.executeQuery();
            while (result.next()) {
                LoanSlipEntity loanSlip = new LoanSlipEntity();
                loanSlip.setId(result.getInt("id"));
                loanSlip.setCustomerID(result.getInt("CustomerID"));
                loanSlip.setBookID(result.getInt("BookID"));
                loanSlip.setBookName(result.getString("BookName"));
                loanSlip.setBookAuthor(result.getString("BookAuthor"));
                loanSlip.setBorrowedDate(result.getDate("BorrowedDate"));
                loanSlip.setExpirationDate(result.getDate("ExpirationDate"));
                loanSlip.setQuantity(result.getInt("Quantity"));
                loanSlip.setIsReturned(result.getInt("isReturned"));
                loanSlip.setIsOnline(result.getInt("isOnline"));
                loanSlipList.add(loanSlip);
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
        return loanSlipList;
    }

    @Override
    public void creatLoanSlip(List<BookModel> LSbookList, int LScheckReader, String LSCustomerID, int online) {

        if (LScheckReader == 1 && !LSbookList.isEmpty()) {
            Connection connect = getConnection();
            for (BookModel book : LSbookList) {
                try {
                    String sql = "INSERT INTO librarymanagement.loanslip (CustomerID, BookID, BookName, BookAuthor, BorrowedDate, ExpirationDate,Quantity,isReturned,isOnline) VALUES (?, ?, ?, ?, ?, ?,?,0,?);";
                    statement = connect.prepareStatement(sql);
                    statement.setString(1, LSCustomerID);
                    statement.setInt(2, book.getId());
                    statement.setString(3, book.getTitle());
                    statement.setString(4, book.getAuthor());
                    LocalDate borrowDate = LocalDate.now();
                    java.sql.Date sqlBorrowDate = java.sql.Date.valueOf(borrowDate);
                    statement.setDate(5, sqlBorrowDate);
                    LocalDate expirationDate = borrowDate.plusDays(30);
                    java.sql.Date sqlExpirationDate = java.sql.Date.valueOf(expirationDate);
                    statement.setDate(6, sqlExpirationDate);
                    statement.setInt(7, book.getQuantity());
                    statement.setInt(8, online);
                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0 && checkQuantity(book.getQuantity(), book.getId())) {
                        deleteQuantity(book.getQuantity(), book.getId());
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Informatin");
                        alert.setHeaderText("Success");
                        alert.setContentText("Thêm thành công");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText("ERROR");
                        alert.setContentText("Thêm thất bại");
                        alert.showAndWait();
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
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Khách hàng không tồn tại hoặc bạn chưa thêm sách để tạo phiếu mượn");
            alert.showAndWait();
        }
    }

    @Override
    public void insertQuantity(Integer quantity, Integer id) {
        Connection connect = getConnection();
        try {
            String sql = "UPDATE books SET Quantity = Quantity + ? WHERE (id = ?);";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, quantity);
            statement.setInt(2, id);

            statement.executeUpdate();

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
    }

    @Override
    public void deleteQuantity(Integer quantity, Integer id) {
        Connection connect = getConnection();
        try {
            String sql = "UPDATE books SET Quantity = Quantity - ? WHERE (id = ?);";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, quantity);
            statement.setInt(2, id);

            statement.executeUpdate();

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
    }

    @Override
    public boolean checkQuantity(Integer quantity, Integer id) {
        Connection connect = getConnection();
        try {
            String sql = "SELECT * FROM librarymanagement.books WHERE id = ?;";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, id);

            result = statement.executeQuery();
            if (result.next()) {
                Integer quantityBook = result.getInt("Quantity");
                if (quantityBook < quantity) {
                    return false;
                } else {
                    return true;
                }
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
        return false;
    }

    @Override
    public void checkOnlineLoanSlip() {
        Connection connect = getConnection();
        try {
            String sql = "SELECT * FROM loanslip WHERE isOnline = 0 AND DATEDIFF(NOW(), BorrowedDate) >= 2;";
            statement = connect.prepareStatement(sql);
            result = statement.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String deleteSql = "DELETE FROM loanslip WHERE id = ?";
                PreparedStatement pstmt = connect.prepareStatement(deleteSql);
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
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

    }

}
