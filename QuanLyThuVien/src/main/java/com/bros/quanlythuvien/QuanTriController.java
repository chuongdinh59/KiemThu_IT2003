package com.bros.quanlythuvien;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class QuanTriController implements Initializable  {

    @FXML
    private void switchToDocGia() throws IOException {
        App.setRoot("DocGiaUI");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        paneslide.setTranslateX(-170);
        bar1.setVisible(true);
        bar2.setVisible(false);
    }

    @FXML
    private void run1(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(paneslide);

        slide.setToX(0);
        slide.play();

        paneslide.setTranslateX(-160);

        slide.setOnFinished((ActionEvent e) -> {
            bar1.setVisible(false);
            bar2.setVisible(true);
        });
    }

    @FXML
    private void run2(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(paneslide);

        slide.setToX(-170);
        slide.play();

        paneslide.setTranslateX(0);

        slide.setOnFinished((ActionEvent e) -> {
            bar1.setVisible(true);
            bar2.setVisible(false);
        });
    }

    @FXML
    private TextField TxtFSearch;
    @FXML
    private Label lbName;
    @FXML
    private Label lbDayofReceipt;
    @FXML
    private Label lbPublished;
    @FXML
    private Label lbType;
    @FXML
    private Label lbPrice;
    @FXML
    private Button QuanTriButton;
    @FXML
    private JFXButton bar2;
    @FXML
    private JFXButton bar1;
    @FXML
    private FontAwesomeIcon font1;
    @FXML
    private FontAwesomeIcon font2;
    @FXML
    private AnchorPane paneslide;

}
