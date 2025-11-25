package edu.icet.controller;

import edu.icet.service.ReportsService;
import edu.icet.service.impl.ReportsServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ReportsController {

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnMedicine;

    @FXML
    private Button btnMedicineReport;

    @FXML
    private Button btnNotification;

    @FXML
    private Button btnNotificationReport;

    @FXML
    private Button btnReports;

    @FXML
    private Button btnSales;

    @FXML
    private Button btnSalesHistory;

    @FXML
    private Button btnSalesReport;

    @FXML
    private Button btnSetting;

    @FXML
    private Button btnSuppliers;

    @FXML
    private Button btnSuppliersReport;

   ReportsService reportsService=new ReportsServiceImpl();

    @FXML
    void btnDashboardOnAction(ActionEvent event) {
        Stage adminDashBoard=new Stage();
        try {
            adminDashBoard.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AdminDashBoard.fxml"))));
            adminDashBoard.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {
        Stage logout=new Stage();
        try {
            logout.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/UserLoginForm.fxml"))));
            logout.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnMedicineOnAction(ActionEvent event) {
        Stage medicine=new Stage();
        try {
            medicine.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Medicine.fxml"))));
            medicine.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnMedicineReportOnAction(ActionEvent event) {
        reportsService.getAll();

    }

    @FXML
    void btnNotificationOnAction(ActionEvent event) {
        Stage notification=new Stage();
        try {
            notification.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Notification.fxml"))));
            notification.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnNotificationReportOnAction(ActionEvent event) {

    }

    @FXML
    void btnReportsOnAction(ActionEvent event) {
        Stage report=new Stage();
        try {
            report.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Reports.fxml"))));
            report.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnSalesHistoryOnAction(ActionEvent event) {
        Stage salesHistory=new Stage();
        try {
            salesHistory.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/SalesHistory.fxml"))));
            salesHistory.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnSalesOnAction(ActionEvent event) {
        Stage sales=new Stage();
        try {
            sales.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Sales.fxml"))));
            sales.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSalesReportOnAction(ActionEvent event) {
        reportsService.getAllSales();

    }

    @FXML
    void btnSettingOnAction(ActionEvent event) {
        Stage settings=new Stage();
        try {
            settings.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Settings.fxml"))));
            settings.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnSuppliersOnAction(ActionEvent event) {
        Stage supplier=new Stage();
        try {
            supplier.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Suppliers.fxml"))));
            supplier.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void btnSuppliersReportOnAction(ActionEvent event) {
        reportsService.getAllSuppiers();

    }

}
