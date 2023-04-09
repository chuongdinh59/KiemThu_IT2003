package com.bros.quanlythuvien;

import com.bros.quanlythuvien.model.ReportModel;
import com.bros.quanlythuvien.repository.LoanSlipRepository;
import com.bros.quanlythuvien.repository.impl.LoanSlipRepositoryImpl;
import com.bros.quanlythuvien.service.LoanSlipService;
import com.bros.quanlythuvien.service.impl.LoanSlipServiceImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;






public class DocGiaController {

    @FXML
    LineChart<String, Number> YearChart;
    
    @FXML
    ComboBox<String> ComboBoxYear;
    
    private LoanSlipService loanSlipService ;
    private List<ReportModel> reportBorrowModels;
    private List<ReportModel> reportReturnModels ;
    public void load () {
        // borrow
        Map<Integer, Integer> mapReport = mapReportYearQuantiry(reportBorrowModels);
        XYChart.Series<String, Number> seriesBorrow = new XYChart.Series<>();
        for (Map.Entry<Integer, Integer> entry : mapReport.entrySet()) {
            seriesBorrow.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));

        }
        seriesBorrow.setName("Số sách mượn mỗi năm");
        YearChart.getData().add(seriesBorrow);
        
        // return chart
        Map<Integer, Integer> mapReturnReport = mapReportYearQuantiry(reportReturnModels);
        XYChart.Series<String, Number> seriesReturn = new XYChart.Series<>();
        for (Map.Entry<Integer, Integer> entry : mapReturnReport.entrySet()) {
            seriesReturn.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
        }
        seriesReturn.setName("Số sách trả mỗi năm");
        YearChart.getData().add(seriesReturn);
        
    }
    public void loadComboBox(){
        Set<String> years = getYears();
        ObservableList<String> options = FXCollections.observableArrayList(
                years
        );  
         // Set default value
        ComboBoxYear.setValue("Chọn năm");
        ComboBoxYear.setItems(options);
        ComboBoxYear.setOnAction(event -> {
            String selectedValue = ComboBoxYear.getValue();
            showPieChartDialog(selectedValue);
        });
    }

    @FXML
    public void initialize() {
        loanSlipService = new LoanSlipServiceImpl();
        reportBorrowModels = loanSlipService.getReportBorrow();
        reportReturnModels =  loanSlipService.getReportReturn();;
        load();
        loadComboBox();
    }
    
    private Map<Integer,Integer> mapReportYearQuantiry(List<ReportModel> reportModels) {
        Map<Integer,Integer> result = new HashMap<>();
        for ( ReportModel report : reportModels) {
            Integer value = result.getOrDefault(report.getYear(), 0);
            result.put(report.getYear(), value + report.getQuantity());
        }
        return result;
    }
    private Map<String,Integer> mapReportQuaterQuantiry(List<ReportModel> reportModels, Integer targetYear) {
        Map<String,Integer> result = new HashMap<>();
        for ( ReportModel report : reportModels) {
            if (Objects.equals(report.getYear(), targetYear)){
                Integer value = result.getOrDefault(report.getQuarter(), 0);
                result.put(report.getQuarter(), value + report.getQuantity());
            }
        }
        return result;
    }
    
//   private void showPieChartDialog(String selectedValue) {
//
//     Map<String, Integer> reportBorrowMap = mapReportQuaterQuantiry(reportBorrowModels, Integer.valueOf(selectedValue));
//    // Create a new Stage for the modal dialog
//    Stage dialog = new Stage();
//    dialog.initModality(Modality.APPLICATION_MODAL);
//    dialog.setTitle("Thông kê trong năm " + selectedValue);
//
//    // Create two new PieCharts and populate them with data
//    PieChart pieChart1 = new PieChart();
//    pieChart1.getData().clear();
//    for (Map.Entry<String, Integer> entry : reportBorrowMap.entrySet()) {
//        PieChart.Data data = new PieChart.Data(entry.getKey(), entry.getValue());
//        Tooltip tooltip = new Tooltip(entry.getKey() + " : " + entry.getValue());
//        tooltip.setStyle("-fx-z-index: 1000;");
//        Tooltip.install(data.getNode(), tooltip);
//        pieChart1.getData().add(data);
//          data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
//              Alert alert = new Alert(AlertType.INFORMATION);
//              alert.setTitle("Data");
//              alert.setContentText(entry.getKey() + " : " + entry.getValue());
//              alert.showAndWait();
//          });
//    }
//
//    Map<String, Integer> reportReturnMap = mapReportQuaterQuantiry(reportReturnModels, Integer.valueOf(selectedValue));
//    PieChart pieChart2 = new PieChart();
//    pieChart2.getData().clear();
//    for (Map.Entry<String, Integer> entry : reportReturnMap.entrySet()) {
//        PieChart.Data data = new PieChart.Data(entry.getKey(), entry.getValue());
////        Tooltip tooltip = new Tooltip(entry.getKey() + " : " + entry.getValue());
////        tooltip.setStyle("-fx-z-index: 1000;");
////        Tooltip.install(data.getNode(), tooltip);
//        pieChart2.getData().add(data);
//          data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
//              Alert alert = new Alert(AlertType.INFORMATION);
//              alert.setTitle("Data");
//              alert.setContentText(entry.getKey() + " : " + entry.getValue());
//              alert.showAndWait();
//          });
//    }
//
//    // Create a new HBox and add the PieCharts to it
//    HBox hBox = new HBox();
//    hBox.getChildren().addAll(pieChart1, pieChart2);
//
//    // Create a new Scene containing the VBox
//    Scene dialogScene = new Scene(new Group(hBox));
//    dialog.setScene(dialogScene);
//    dialog.show();
//}

   
    private void showPieChartDialog(String selectedValue) {

     Map<String, Integer> reportBorrowMap = mapReportQuaterQuantiry(reportBorrowModels, Integer.valueOf(selectedValue));
    // Create a new Stage for the modal dialog
    Stage dialog = new Stage();
    dialog.initModality(Modality.APPLICATION_MODAL);
    dialog.setTitle("Thông kê trong năm " + selectedValue);
    
    // Create two new PieCharts and populate them with data
    PieChart pieChart1 = new PieChart();
    pieChart1.setTitle("Thống kê số sách mượn theo quý trong năm " + selectedValue);
    for (Map.Entry<String, Integer> entry : reportBorrowMap.entrySet()) {
           PieChart.Data data = new PieChart.Data(entry.getKey(), entry.getValue());
            pieChart1.getData().add(data);
//            Tooltip tooltip = new Tooltip(entry.getKey() + ": " + entry.getValue());
//            System.out.println("Tooltip message: " + tooltip.getText());
//            tooltip.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-color: black;");
//            Tooltip.install(data.getNode(), tooltip); // set tooltip for data node
            
          data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
              Alert alert = new Alert(AlertType.INFORMATION);
              alert.setTitle("Data");
              alert.setContentText(entry.getKey() + " : " + entry.getValue() +  " quyển sách");
              alert.showAndWait();
          });
    }

    Map<String, Integer> reportReturnMap = mapReportQuaterQuantiry(reportReturnModels, Integer.valueOf(selectedValue));
    PieChart pieChart2 = new PieChart();
    pieChart2.setTitle("Thống kê số sách trả theo quý trong năm " + selectedValue);
    for (Map.Entry<String, Integer> entry : reportReturnMap.entrySet()) {
        PieChart.Data data = new PieChart.Data(entry.getKey(), entry.getValue());
        pieChart2.getData().add(data);
//        Tooltip tooltip = new Tooltip(entry.getKey() + ": " + entry.getValue());
//        tooltip.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-color: black;");
//        System.out.println("Tooltip message: " + tooltip.getText());
//        Tooltip.install(data.getNode(), tooltip); // set tooltip for data node

        data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                  Alert alert = new Alert(AlertType.INFORMATION);
                  alert.setTitle("Data");
                  alert.setContentText(entry.getKey() + " : " + entry.getValue() + " quyển sách");
                  alert.showAndWait();
            });
    }

    // Create a new HBox and add the PieCharts to it
    HBox hBox = new HBox();
    hBox.getChildren().addAll(pieChart1, pieChart2);
    // Create a new Scene containing the VBox
    Scene dialogScene = new Scene(new Group(hBox));
    dialog.setScene(dialogScene);
    dialog.show();
}

    private Set<String> getYears () {
        Set<String> years = new HashSet<>();
        for ( ReportModel report : reportBorrowModels) {
            years.add(report.getYear().toString());
        }
       return years;
    }
    
}
