package com.bros.quanlythuvien;



import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class DocGiaController {

    @FXML
     private void switchToQuanTri() throws IOException {
        App.setRoot("QuanTriUI");
    }


//    @FXML
//    private Label lbMaDG;
//    @FXML
//    private Label lbDoiTuong;
//    @FXML
//    private Label lbNgaySinh;
//    @FXML
//    private Label lbGioiTinh;
//    @FXML
//    private Label lbBoPhan;
//    @FXML
//    private Label lbHanThe;
//    @FXML
//    private Label lbHoTen;
//    @FXML
//    private TextField txtFDiaChi;
//    @FXML
//    private TextField txtFEmail;
//    @FXML
//    private TextField txtFDienThoai;
//    @FXML
//    private Button DocGiaButton;
    
    // Test
    @FXML
    ImageView imgImageView;
    @FXML
    private Button Submit;
    
    
    private Cloudinary cloudinary;
    public void initialize() {
            cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dgqhgtpal",
                "api_key", "529593322851467",
                "api_secret", "SiN6khl_NrU7bpgawwi0g-R10uo"
        ));
     }
    public void handleClick () {
        initialize();
        FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Image File");
            File file = fileChooser.showOpenDialog(new Stage());

            if (file != null) {
                try {
                    // Upload the image file to Cloudinary
                    Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
                    // Get the URL of the uploaded image and display it in the ImageView
                    String imageUrl = (String) uploadResult.get("url");
                    System.out.print(imageUrl);
                    imgImageView.setImage(new Image(imageUrl));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }
}
