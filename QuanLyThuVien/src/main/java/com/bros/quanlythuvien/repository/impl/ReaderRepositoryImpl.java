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
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

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

    @Override
    public Map<String, Object> login(TextField username, TextField password, Button loginBtn) {
        Connection connect = getConnection();
        Map<String, Object> resultMap = new HashMap<>();
        try {
            String sql = "SELECT * FROM account WHERE user_name = ? and password = ?";
            statement = connect.prepareStatement(sql);
            statement.setString(1, username.getText());
            statement.setString(2, password.getText());
            result = statement.executeQuery();
            if (result.next()) {
                String accountType = result.getString("type");
                // Lưu trữ readerId vào biến
                Integer readerId = result.getInt("ReaderID");
                if (accountType.equals("Admin")) {
                    // Chuyển hướng đến trang quản trị viên
                    loginBtn.getScene().getWindow().hide();
                    resultMap.put("type", "Admin");
                } else if (accountType.equals("Employee")) {
                    // Chuyển hướng đến trang nhân viên
                    loginBtn.getScene().getWindow().hide();
                    resultMap.put("type", "Employee");
                } else if (accountType.equals("Customer")) {
                    loginBtn.getScene().getWindow().hide();
                    resultMap.put("type", "Customer");
                    resultMap.put("readerId", readerId);
                }

            } else {
                resultMap.put("type", "Error");
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
        return resultMap;
    }

    @Override
    public int register(TextField register_username, TextField register_password, TextField register_fullname, TextField register_email) {
        Connection connect = getConnection();
        try {
            // Kiểm tra các trường dữ liệu
            if (register_username.getText().isEmpty() || register_password.getText().isEmpty()
                    || register_fullname.getText().isEmpty() || register_email.getText().isEmpty()) {
                return 1;
            }

            // Kiểm tra username hoặc email có bị trùng
            String sql = "SELECT * FROM account WHERE user_name = ? OR email = ?";
            PreparedStatement checkStatement = connect.prepareStatement(sql);
            checkStatement.setString(1, register_username.getText());
            checkStatement.setString(2, register_email.getText());
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next()) {
                return 2;
            }
            //Tạo 1 reader
            String insertReader = "INSERT INTO readers (FullName) VALUES (?)";
            PreparedStatement insertReaderStatement = connect.prepareStatement(insertReader, Statement.RETURN_GENERATED_KEYS);
            insertReaderStatement.setString(1, register_fullname.getText());
            int resultReader = insertReaderStatement.executeUpdate();
            if (resultReader > 0) {
                ResultSet generatedKeys = insertReaderStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int readerId = generatedKeys.getInt(1);
                    String insertSql = "INSERT INTO account (user_name, password, full_name, email, type,ReaderID) VALUES (?, ?, ?, ?, 'Customer',?)";
                    PreparedStatement insertStatement = connect.prepareStatement(insertSql);
                    insertStatement.setString(1, register_username.getText());
                    insertStatement.setString(2, register_password.getText());
                    insertStatement.setString(3, register_fullname.getText());
                    insertStatement.setString(4, register_email.getText());
                    insertStatement.setInt(5, readerId);
                    int result1 = insertStatement.executeUpdate();
                    if (result1 > 0) {
                        return 3;
                    } else {
                        return 4;
                    }
                }
            } else {
                return 4;
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
        return 0;
    }

    @Override
    public boolean updateReader(ReaderModel reader) {
        Connection connect = getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "UPDATE readers SET FullName = ? , Gender = ? ,DateOfBirth = ? WHERE id = ?;";
            statement = connect.prepareStatement(sql);
            statement.setString(1, reader.getFullname());
            statement.setString(2, reader.getGender());
            statement.setString(3, reader.getDateOfBirth());
            statement.setInt(4, reader.getId());

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
        return false;
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
