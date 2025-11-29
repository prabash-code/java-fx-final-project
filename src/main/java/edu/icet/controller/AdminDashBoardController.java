package edu.icet.controller;

import edu.icet.model.dto.Medicine;
import edu.icet.service.MedicineService;
import edu.icet.service.impl.MedicineServiceImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AdminDashBoardController implements Initializable {


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
    private TableColumn<Medicine, String> colIssue;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private Label lblUser;

    @FXML
    private TableView<Medicine> tblAdminIssue;

    @FXML
    private BarChart<?, ?> chartAdminSalesDetails;

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
        Stage logout = new Stage();
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
        Stage notification = new Stage();
        try {
            notification.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Notification.fxml"))));
            notification.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    public void btnReportsOnAction(ActionEvent event) {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MedicineService medicineService = new MedicineServiceImpl();
        XYChart.Series set1 = new XYChart.Series<>();

        ObservableList<Medicine> medicineNameList = medicineService.getAll();
        for (Medicine medicine : medicineNameList) {
            set1.getData().add(new XYChart.Data(medicine.getName(), medicine.getQuantity()));
        }
        chartAdminSalesDetails.getData().addAll(set1);

        colId.setCellValueFactory(new PropertyValueFactory<>("medicineId"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("name"));
        colName.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colIssue.setCellValueFactory(cellData -> {
            LocalDate expireDate = cellData.getValue().getExpireDate();
            LocalDate date = LocalDate.now();
            String issue;
            if (expireDate.isBefore(date)) {
                issue = "Recently Expired";
            } else if (!expireDate.isAfter(date.plusDays(20))) {
                issue = "Expiring Soon";

            } else {
                issue = "Valid";
            }
            return new SimpleStringProperty(issue);
        });

        tblAdminIssue.setItems(medicineService.getAll());

    }

}
