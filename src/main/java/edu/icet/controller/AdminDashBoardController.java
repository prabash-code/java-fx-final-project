package edu.icet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashBoardController {

    @FXML
    private LineChart<?, ?> adminChart;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnLogout;


    @FXML
    private Button btnMedicine;



    @FXML
    private Button btnNotification;

    @FXML
    private Button btnReports;

    @FXML
    private Button btnSales;

    @FXML
    private Button btnSalesHistory;

    @FXML
    private Button btnSetting;

    @FXML
    private Button btnSupplies;

    @FXML
    private TableColumn<?, ?> colBrand;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colIssue;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    void adminChartOnAction(MouseEvent event) {

    }

    @FXML
    void bntSupplyOnAction(ActionEvent event) {
        Stage stageSuppliers = new Stage();
        try {
            stageSuppliers.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Suppliers.fxml"))));
            stageSuppliers.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) {

    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {
        Stage logout= new Stage();
        try {
            logout.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/UserLoginForm.fxml"))));
            logout.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnMedicineOnAction(ActionEvent event) {
        Stage stageMedicine = new Stage();
        try {
            stageMedicine.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Medicine.fxml"))));
            stageMedicine.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnNotificationOnAction(ActionEvent event) {
        Stage notification  = new Stage();
        try {
            notification.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Notification.fxml"))));
            notification.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnReportsOnAction(ActionEvent event) {
        Stage reports = new Stage();
        try {
            reports.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Reports.fxml"))));
            reports.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnSalesHistoryOnAction(ActionEvent event) {
        Stage salesHistory = new Stage();
        try {
            salesHistory.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/SalesHistory.fxml"))));
            salesHistory.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnSalesOnAction(ActionEvent event) {
        Stage stageSales = new Stage();
        try {
            stageSales.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Sales.fxml"))));
            stageSales.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnSettingOnAction(ActionEvent event) {
        Stage settings = new Stage();
        try {
            settings.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Settings.fxml"))));
            settings.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
