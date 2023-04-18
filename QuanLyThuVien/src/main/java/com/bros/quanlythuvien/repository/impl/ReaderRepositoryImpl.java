/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.repository.impl;

import com.bros.quanlythuvien.entity.AccountEntity;
import com.bros.quanlythuvien.entity.ReaderEntity;
import com.bros.quanlythuvien.model.AccountModel;
import com.bros.quanlythuvien.model.ReaderModel;
import com.bros.quanlythuvien.repository.BorrowCardRepository;
import com.bros.quanlythuvien.repository.ReaderRepository;
import com.bros.quanlythuvien.utils.BcryptUtils;
import static com.bros.quanlythuvien.utils.ConnectionUtils.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author phu nguyen
 */
public class ReaderRepositoryImpl extends CommonRepositoryImpl<ReaderEntity> implements ReaderRepository {

    private BorrowCardRepository borrowCardRepository = new BorrowCardRepositoryImpl();

    @Override
    public List<ReaderEntity> findReaderNotHaveBorrowCard() {
        List<ReaderEntity> readers = new ArrayList<>();
        List<Integer> myList = borrowCardRepository.findreaderId();
        List<Integer> idList = findReaderId();
        List<Integer> myReaderList = new ArrayList<>();
        for (Integer id : idList) {
            if (!myList.contains(id)) {
                myReaderList.add(id);
            }
        }
        for (Integer id : myReaderList) {
            ReaderEntity reader = findById(id);
            readers.add(reader);
        }

        return readers;
    }

    @Override
    public List<Integer> findReaderId() {
        Connection connect = getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        List<Integer> idList = new ArrayList<>();
        try {
            String sql = "SELECT id FROM readers;";
            statement = connect.prepareStatement(sql);
            result = statement.executeQuery();
            while (result.next()) {
                int readerID = result.getInt("id");
                idList.add(readerID);
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
        return idList;
    }

    @Override
    public AccountEntity findAccountByRId(Integer id) {
        Connection connect = getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        AccountEntity account = new AccountEntity();
        try {
            String sql = "SELECT * FROM account WHERE ReaderID = ?";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, id);
            result = statement.executeQuery();
            if (result.next()) {
                account.setId(result.getInt("id"));
                account.setUserName(result.getString("user_name"));
                account.setPassword(result.getString("password"));
                account.setFullName(result.getString("full_name"));
                account.setEmail(result.getString("email"));
                account.setType(result.getString("type"));
                account.setReaderID(id);
                return account;
            } else {
                return null;
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
        return account;
    }

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
    public Map<Integer, String> loadCate(Map<Integer, String> categoriesMap) {
        Connection connect = getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
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
            return categoriesMap;
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
        return categoriesMap;
    }

    @Override
    public int checkReader(Integer id) {
        Connection connect = getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
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
    public Map<String, Object> login(String username, String password) {
        Connection connect = getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        Map<String, Object> resultMap = new HashMap<>();
        try {
            String sql = "SELECT * FROM account WHERE user_name = ? ";
            statement = connect.prepareStatement(sql);
            statement.setString(1, username);
            result = statement.executeQuery();
            if (result.next()) {
                String hashedPassword = result.getString("password");
                Boolean isLogin = BcryptUtils.matchPassword(password, hashedPassword, username);
                if (isLogin) {
                    String accountType = result.getString("type");
                    // Lưu trữ readerId vào biến
                    Integer readerId = result.getInt("ReaderID");
                    if (accountType.equals("Admin")) {
                        // Chuyển hướng đến trang quản trị viên
                        resultMap.put("type", "Admin");
                    } else if (accountType.equals("Employee")) {
                        // Chuyển hướng đến trang nhân viên
//                        loginBtn.getScene().getWindow().hide();
                        resultMap.put("type", "Employee");
                    } else if (accountType.equals("Customer")) {
                        resultMap.put("type", "Customer");
                        resultMap.put("readerId", readerId);
                    }
                } else {
                    resultMap.put("type", "Error");
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
    public int register(String register_username, String register_password, String register_fullname, String register_email) {
        Connection connect = getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            // Kiểm tra các trường dữ liệu
            if (register_username.isEmpty() || register_password.isEmpty()
                    || register_fullname.isEmpty() || register_email.isEmpty()) {
                return 1;
            }

            // Kiểm tra username hoặc email có bị trùng
            String sql = "SELECT * FROM account WHERE user_name = ? OR email = ?";
            PreparedStatement checkStatement = connect.prepareStatement(sql);
            checkStatement.setString(1, register_username);
            checkStatement.setString(2, register_email);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next()) {
                return 2;
            }
            //Tạo 1 reader
            String insertReader = "INSERT INTO readers (FullName) VALUES (?)";
            PreparedStatement insertReaderStatement = connect.prepareStatement(insertReader, Statement.RETURN_GENERATED_KEYS);
            insertReaderStatement.setString(1, register_fullname);
            int resultReader = insertReaderStatement.executeUpdate();
            if (resultReader > 0) {
                ResultSet generatedKeys = insertReaderStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int readerId = generatedKeys.getInt(1);
                    String insertSql = "INSERT INTO account (user_name, password, full_name, email, type,ReaderID) VALUES (?, ?, ?, ?, 'Customer',?)";
                    PreparedStatement insertStatement = connect.prepareStatement(insertSql);
                    String hashedPassword = BcryptUtils.encryptPassword(register_password.trim(), register_username.trim());

                    System.out.print(hashedPassword);

                    insertStatement.setString(1, register_username);
                    insertStatement.setString(2, hashedPassword);
                    insertStatement.setString(3, register_fullname);
                    insertStatement.setString(4, register_email);
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
        return 4;
    }

    @Override
    public boolean updateReader(ReaderModel reader) {
        Connection connect = getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "UPDATE readers SET FullName = ? , Gender = ? ,DateOfBirth = ?,Phone =? WHERE id = ?;";
            statement = connect.prepareStatement(sql);
            statement.setString(1, reader.getFullname());
            statement.setString(2, reader.getGender());
            statement.setString(3, reader.getDateOfBirth());
            statement.setString(4, reader.getPhone());
            statement.setInt(5, reader.getId());

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
    
     @Override
    public boolean updateRoleAccount(String role,Integer id) {
        Connection connect = getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "UPDATE account SET type = ? WHERE ReaderID = ?;";
            statement = connect.prepareStatement(sql);
            statement.setString(1, role);
            statement.setInt(2, id);
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

}
