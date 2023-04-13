/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien;

import com.bros.quanlythuvien.repository.ReaderRepository;
import com.bros.quanlythuvien.repository.impl.ReaderRepositoryImpl;
import com.bros.quanlythuvien.service.LoanSlipService;
import com.bros.quanlythuvien.service.ReaderService;
import com.bros.quanlythuvien.service.impl.LoanSlipServiceImpl;
import com.bros.quanlythuvien.service.impl.ReaderServiceImpl;
import com.bros.quanlythuvien.utils.MessageBoxUtils;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    private ReaderRepository readerRepository = new ReaderRepositoryImpl();

//    Hàm
    public Map<String, Object> login(TextField username, TextField password, Button loginBtn) {
        String u = username.getText();
        String p = password.getText();
        Map<String, Object> resultMap = readerRepository.login(u, p);
        loginBtn.getScene().getWindow().hide();
        return resultMap;
    }

    public int register(TextField register_username, TextField register_password, TextField register_fullname, TextField register_email) {
        String u = register_username.getText();
        String p = register_password.getText();
        String f = register_fullname.getText();
        String e = register_email.getText();

        int rs = readerRepository.register(u, p, f, e);
        return rs;
    }
//    -------------------------

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
        Map<String, Object> resultMap = login(username, password, loginBtn);
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
            MessageBoxUtils.AlertBox("Error", "Sai tài khoản hoặc mật khẩu", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void register(ActionEvent evt) {

        int rs = register(register_username, register_password, register_fullname, register_email);
        switch (rs) {
            case 1: {
                MessageBoxUtils.AlertBox("Error", "Bạn phải nhập đầy đủ thông tin để đăng ký", Alert.AlertType.ERROR);
                break;
            }
            case 2: {
                MessageBoxUtils.AlertBox("Error", "Tên đăng nhập hoặc email đã tồn tại", Alert.AlertType.ERROR);
                break;
            }
            case 3: {
                MessageBoxUtils.AlertBox("INFORMATION", "Đăng ký thành công", Alert.AlertType.INFORMATION);
                login_form.setVisible(true);
                register_form.setVisible(false);
                break;
            }
            case 4: {
                MessageBoxUtils.AlertBox("Error", "Đăng ký thất bại", Alert.AlertType.ERROR);
                break;
            }
            default:
                break;
        }

    }
}
