/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien;

import static com.bros.quanlythuvien.utils.ConnectionUtils.getConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
    private Button close;

    @FXML
    private Button loginBtn;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Label lbChangePosition;

    @FXML
    private ComboBox<String> cbxPosition;

    ObservableList<String> list = FXCollections.observableArrayList("Admin", "Employee", "Customer");
    private PreparedStatement statement;
    private ResultSet result;

    @FXML
    public void close() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbxPosition.setItems(list);
    }

    @FXML
    public void comboBoxChange(ActionEvent event) {
        lbChangePosition.setText(cbxPosition.getValue());
    }

    @FXML
    public void login(ActionEvent evt) {
        Connection connect = getConnection();
        try {
            String sql = "SELECT * FROM account WHERE user_name = ? and password = ?";
            statement = connect.prepareStatement(sql);
            statement.setString(1, username.getText());
            statement.setString(2, password.getText());
            result = statement.executeQuery();
            if(result.next()){
                loginBtn.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("QuanTriUI.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Admin");
                stage.show();
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Lỗi đăng nhập");
                alert.setHeaderText("Error");
                alert.setContentText("Sai tài khoản hoặc mật khẩu");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
