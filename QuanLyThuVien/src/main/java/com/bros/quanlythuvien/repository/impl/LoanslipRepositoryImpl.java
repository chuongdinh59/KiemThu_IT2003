/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.repository.impl;

import com.bros.quanlythuvien.entity.LoanSlipEntity;
import com.bros.quanlythuvien.model.BookModel;
import com.bros.quanlythuvien.model.LoanSlipModel;
import com.bros.quanlythuvien.model.ReportModel;
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
import com.bros.quanlythuvien.repository.LoanSlipRepository;

/**
 *
 * @author ADMIN
 */
public class LoanSlipRepositoryImpl extends CommonRepositoryImpl<LoanSlipEntity> implements LoanSlipRepository {

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
    public Integer updateBook(LoanSlipModel loanSlip) {
        Connection connect = getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        Integer targetDayBetween = 0;
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

                Long daysBetween = (sqlDate.getTime() - sqlTimestamp.getTime()) / (1000 * 60 * 60 * 24);
                if (daysBetween > 0) {
                    // Tối thiểu là 1
                    targetDayBetween = Integer.valueOf(daysBetween.toString());
//                    MessageBoxUtils.AlertBox("ERROR", "Bạn đã trễ hạn " + daysBetween + " ngày và tiền phạt là: " + 5000 * daysBetween + " VNĐ", Alert.AlertType.ERROR);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(LoanSlipRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            String sql = "UPDATE loanslip SET isReturned = '1',isOnline ='1' WHERE (id = ?);";

            statement = connect.prepareStatement(sql);

            statement.setInt(1, loanSlip.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0 && targetDayBetween != 0) {
                return targetDayBetween;
            }
            if (rowsUpdated > 0) {
                insertQuantity(loanSlip.getQuantity(), loanSlip.getBookID());
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
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
        return -1;
    }

    @Override
    public boolean updateBookGive(LoanSlipModel loanSlip) {
        Connection connect = getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        if (loanSlip == null) {
            return false;
        }

        try {
            String sql = "UPDATE loanslip SET isOnline = '1' WHERE (id = ?);";
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
        PreparedStatement statement = null;
        ResultSet result = null;
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
        PreparedStatement statement = null;
        ResultSet result = null;
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
    public Integer creatLoanSlip(List<BookModel> LSbookList, int LScheckReader, String LSCustomerID, int online) {
        if (LScheckReader == 1 && !LSbookList.isEmpty()) {
            for (int i = 0; i < LSbookList.size(); i++) {
                BookModel book = LSbookList.get(i);
                if (checkQuantity(book.getQuantity(), book.getId())) {
                    Connection connect = getConnection();
                    PreparedStatement statement = null;
                    ResultSet result = null;
                    try {
                        String sql = "INSERT INTO loanslip (CustomerID, BookID, BookName, BookAuthor, BorrowedDate, ExpirationDate,Quantity,isReturned,isOnline) VALUES (?, ?, ?, ?, ?, ?,?,0,?);";
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
                        if (rowsInserted > 0) {
                            deleteQuantity(book.getQuantity(), book.getId());
                            if (i == LSbookList.size() - 1) {
                                return 1;
                            }
//                            MessageBoxUtils.AlertBox("INFORMATION", "Thêm thành công", Alert.AlertType.INFORMATION);
//                            return true;
                        } else {
                            return -1;
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        return -1;
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
                } else {
                    return 2;
//                    MessageBoxUtils.AlertBox("ERROR", "Thư viện không đủ sách", Alert.AlertType.ERROR);
                }

            }
        } else {
            return 3;
//            MessageBoxUtils.AlertBox("ERROR", "Khách hàng không tồn tại hoặc bạn chưa thêm sách để tạo phiếu mượn", Alert.AlertType.ERROR);
        }
//        return false;
        return -1;
    }

    @Override
    public void insertQuantity(Integer quantity, Integer id
    ) {
        Connection connect = getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
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
    public void deleteQuantity(Integer quantity, Integer id
    ) {
        Connection connect = getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
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
    public Boolean checkQuantity(Integer quantity, Integer id
    ) {
        Connection connect = getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            String sql = "SELECT * FROM books WHERE id = ?;";
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
    public int checkOnlineLoanSlip() {
        int numOfDeleted = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            String sql = "SELECT * FROM loanslip WHERE isOnline = 0 AND DATEDIFF(NOW(), ExpirationDate) >= 2;";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                insertQuantity(resultSet.getInt("Quantity"), resultSet.getInt("BookID"));
                String deleteSql = "DELETE FROM loanslip WHERE id = ?";
                PreparedStatement pstmt = connection.prepareStatement(deleteSql);
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
                numOfDeleted++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return numOfDeleted;
    }

    @Override
    public List<ReportModel> getReportBorrow() {
        List<ReportModel> reports = new ArrayList<>();
        try {
            Connection conn = getConnection();
            String sql = "SELECT YEAR(BorrowedDate) AS Year, CONCAT('Qúy ', QUARTER(BorrowedDate)) AS Quarter, "
                    + "SUM(Quantity) AS NumReturnedBooks FROM loanslip GROUP BY YEAR(BorrowedDate), QUARTER(BorrowedDate)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            // Xử lý dữ liệu lấy được ở đây
            while (rs.next()) {
                ReportModel report = new ReportModel();
                report.setYear(rs.getInt("Year"));
                report.setQuarter(rs.getString("Quarter"));
                report.setQuantity(rs.getInt("NumReturnedBooks"));
                // Thêm đối tượng report vào danh sách hoặc làm gì đó khác với dữ liệu này tại đây
                reports.add(report);
            }

            // Đóng kết nối, PreparedStatement và ResultSet
            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reports;
    }

    @Override
    public List<ReportModel> getReportReturn() {
        List<ReportModel> reports = new ArrayList<>();
        try {
            Connection conn = getConnection();
            String sql = "SELECT YEAR(BorrowedDate) as Year, CONCAT('Qúy ', QUARTER(BorrowedDate)) AS Quarter, "
                    + "SUM(quantity) as NumReturnedBooks FROM loanslip WHERE isReturned = 1 "
                    + "GROUP BY YEAR(BorrowedDate), QUARTER(BorrowedDate)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            // Xử lý dữ liệu lấy được ở đây
            while (rs.next()) {
                ReportModel report = new ReportModel();
                report.setYear(rs.getInt("Year"));
                report.setQuarter(rs.getString("Quarter"));
                report.setQuantity(rs.getInt("NumReturnedBooks"));
                // Thêm đối tượng report vào danh sách hoặc làm gì đó khác với dữ liệu này tại đây
                reports.add(report);
            }

            // Đóng kết nối, PreparedStatement và ResultSet
            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reports;
    }

    @Override
    public List<LoanSlipEntity> findByBookIDAndReaderID(Integer bookID, Integer readerID) {
        List<LoanSlipEntity> result = new ArrayList<>();
        try (Connection conn = getConnection()) {
            StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM loanslip WHERE 1 = 1");
            if (bookID != null) {
                sqlBuilder.append(" AND BookID = ?");
            }
            if (readerID != null) {
                sqlBuilder.append(" AND CustomerID = ?");
            }
            try (PreparedStatement ps = conn.prepareStatement(sqlBuilder.toString())) {
                int parameterIndex = 1;
                if (bookID != null) {
                    ps.setInt(parameterIndex++, bookID);
                }
                if (readerID != null) {
                    ps.setInt(parameterIndex++, readerID);
                }
                System.err.println(sqlBuilder.toString());
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        LoanSlipEntity loanSlip = new LoanSlipEntity();
                        loanSlip.setId(rs.getInt("id"));
                        loanSlip.setCustomerID(rs.getInt("CustomerID"));
                        loanSlip.setBookID(rs.getInt("BookID"));
                        loanSlip.setBookName(rs.getString("BookName"));
                        loanSlip.setBookAuthor(rs.getString("BookAuthor"));
                        loanSlip.setBorrowedDate(rs.getDate("BorrowedDate"));
                        loanSlip.setExpirationDate(rs.getDate("ExpirationDate"));
                        loanSlip.setQuantity(rs.getInt("Quantity"));
                        loanSlip.setIsReturned(rs.getInt("isReturned"));
                        loanSlip.setIsOnline(rs.getInt("isOnline"));
                        result.add(loanSlip);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
