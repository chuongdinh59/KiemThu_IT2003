<<<<<<<< HEAD:QuanLyThuVien/src/main/java/com/bros/quanlythuvien/App.java
package com.bros.quanlythuvien;
========
package com.mycompany.quanlythuvien;
>>>>>>>> c00f4dcbc1c99412a3ee9aeba31649538989cf9d:QuanLyThuVien/src/main/java/com/mycompany/quanlythuvien/App.java

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

    @Override
    public void start(Stage stage) throws IOException {
<<<<<<<< HEAD:QuanLyThuVien/src/main/java/com/bros/quanlythuvien/App.java
        scene = new Scene(loadFXML("DocGiaUI"), 640, 480);
========
        scene = new Scene(loadFXML("DocGiaUI"));
>>>>>>>> c00f4dcbc1c99412a3ee9aeba31649538989cf9d:QuanLyThuVien/src/main/java/com/mycompany/quanlythuvien/App.java
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