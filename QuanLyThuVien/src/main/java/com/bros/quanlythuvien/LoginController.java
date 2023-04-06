/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien;

import com.bros.quanlythuvien.service.LoanSlipService;
import com.bros.quanlythuvien.service.ReaderService;
import com.bros.quanlythuvien.service.impl.LoanSlipServiceImpl;
import com.bros.quanlythuvien.service.impl.ReaderServiceImpl;
import static com.bros.quanlythuvien.utils.ConnectionUtils.getConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author ADMIN
 */
public class LoginController implements Initializable {

    @FXML
    private Button BacktoLoginBtn;

    @FXML
    private Button RegisterAccountBtn;

    @FXML
    private Button close;

    @FXML
    private Label lbChangePosition;

    @FXML
    private Button loginBtn;

    @FXML
    private AnchorPane login_form;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private PasswordField password;

    @FXML
    private Button registerBtn;

    @FXML
    private TextField register_email;

    @FXML
    private AnchorPane register_form;

    @FXML
    private TextField register_fullname;

    @FXML
    private PasswordField register_password;

    @FXML
    private TextField register_username;

    @FXML
    private TextField username;

    private LoanSlipService loanSlipService = new LoanSlipServiceImpl();
    private ReaderService readerService = new ReaderServiceImpl();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loanSlipService.checkOnlineLoanSlip();
    }

    @FXML
    public void close() {
        System.exit(0);
    }

    @FXML
    public void switchForm(ActionEvent event) {
        if (event.getSource() == RegisterAccountBtn) {
            login_form.setVisible(false);
            register_form.setVisible(true);
        }
        if (event.getSource() == BacktoLoginBtn) {
            login_form.setVisible(true);
            register_form.setVisible(false);
        }
    }

    @FXML
    public void login(ActionEvent evt) throws IOException {
        Map<String, Object> resultMap = readerService.login(username, password, loginBtn);
        if (resultMap.get("type").equals("Admin")) {
            Parent root = FXMLLoader.load(getClass().getResource("QuanTriSachUI.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Admin");
            stage.show();
        } else if (resultMap.get("type").equals("Employee")) {
            Parent root = FXMLLoader.load(getClass().getResource("EmployeeUI.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Employee");
            stage.show();
        } else if (resultMap.get("type").equals("Customer")) {
            Integer reader = (Integer) resultMap.get("readerId");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerUI.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            // Truyền readerId cho trang CustomerUI
            CustomerController customerController = loader.getController();
            customerController.setReaderId(reader);

            stage.setScene(scene);
            stage.setTitle("Customer");
            stage.show();
        } else if (resultMap.get("type").equals("Error")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Lỗi đăng nhập");
            alert.setHeaderText("Error");
            alert.setContentText("Sai tài khoản hoặc mật khẩu");
            alert.showAndWait();
        }
    }

    @FXML
    public void register(ActionEvent evt) {

        int rs = readerService.register(register_username, register_password, register_fullname, register_email);
        switch (rs) {
            case 1:
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Lỗi đăng ký");
                    alert.setHeaderText("Error");
                    alert.setContentText("Bạn phải nhập đầy đủ thông tin để đăng ký");
                    alert.showAndWait();
                    break;
                }
            case 2:
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Lỗi đăng ký");
                    alert.setHeaderText("Error");
                    alert.setContentText("Tên đăng nhập hoặc email đã tồn tại");
                    alert.showAndWait();
                    break;
                }
            case 3:
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Register");
                    alert.setHeaderText("Successful");
                    alert.setContentText("Đăng ký thành công");
                    alert.showAndWait();
                    login_form.setVisible(true);
                    register_form.setVisible(false);
                    break;
                }
            case 4:
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Lỗi đăng ký");
                    alert.setHeaderText("Error");
                    alert.setContentText("Đăng ký thất bại");
                    alert.showAndWait();
                    break;
                }
            default:
                break;
        }

    }
//        Connection connect = getConnection();
//        try {
//            // Kiểm tra các trường dữ liệu
//            if (register_username.getText().isEmpty() || register_password.getText().isEmpty()
//                    || register_fullname.getText().isEmpty() || register_email.getText().isEmpty()) {
//                Alert alert = new Alert(Alert.AlertType.WARNING);
//                alert.setTitle("Lỗi đăng ký");
//                alert.setHeaderText("Error");
//                alert.setContentText("Bạn phải nhập đầy đủ thông tin để đăng ký");
//                alert.showAndWait();
//                return;
//            }
//
//            // Kiểm tra username hoặc email có bị trùng
//            String sql = "SELECT * FROM librarymanagement.account WHERE user_name = ? OR email = ?";
//            PreparedStatement checkStatement = connect.prepareStatement(sql);
//            checkStatement.setString(1, register_username.getText());
//            checkStatement.setString(2, register_email.getText());
//            ResultSet resultSet = checkStatement.executeQuery();
//            if (resultSet.next()) {
//                Alert alert = new Alert(Alert.AlertType.WARNING);
//                alert.setTitle("Lỗi đăng ký");
//                alert.setHeaderText("Error");
//                alert.setContentText("Tên đăng nhập hoặc email đã tồn tại");
//                alert.showAndWait();
//                return;
//            }
//
//            // Nếu không có lỗi, thực hiện lệnh insert
//            String insertSql = "INSERT INTO librarymanagement.account (user_name, password, full_name, email, type) VALUES (?, ?, ?, ?, 'Customer')";
//            PreparedStatement insertStatement = connect.prepareStatement(insertSql);
//            insertStatement.setString(1, register_username.getText());
//            insertStatement.setString(2, register_password.getText());
//            insertStatement.setString(3, register_fullname.getText());
//            insertStatement.setString(4, register_email.getText());
//            int result1 = insertStatement.executeUpdate();
//            if (result1 > 0) {
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Register");
//                alert.setHeaderText("Successful");
//                alert.setContentText("Đăng ký thành công");
//                alert.showAndWait();
//                login_form.setVisible(true);
//                register_form.setVisible(false);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
