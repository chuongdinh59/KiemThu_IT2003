package com.bros.quanlythuvien;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
//    private double x = 0;
//    private double y = 0;
//    private static Parent root;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("QuanTriUI"));
//            Parent root = FXMLLoader.load(getClass().getResource("DashBoardUI.fxml"))


//        root.setOnMousePressed((MouseEvent event) -> {
//            x = event.getSceneX();
//            y = event.getSceneY();
//        });
//        root.setOnMouseDragged((MouseEvent event) -> {
//            stage.setX(event.getSceneX() - x);
//            stage.setY(event.getSceneY() - y);
//
//            stage.setOpacity(.8);
//        });
//        
//        root.setOnMouseReleased((MouseEvent event) ->{
//            stage.setOpacity(1);
//        });

        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
