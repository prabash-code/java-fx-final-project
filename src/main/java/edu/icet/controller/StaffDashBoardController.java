package edu.icet.controller;

import edu.icet.model.dto.Medicine;
import edu.icet.model.dto.Notification;
import edu.icet.model.dto.Sale;
import edu.icet.service.MedicineService;
import edu.icet.service.NotificationService;
import edu.icet.service.SalesService;
import edu.icet.service.impl.MedicineServiceImpl;
import edu.icet.service.impl.NotificationServiceImpl;
import edu.icet.service.impl.SalesServiceImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class StaffDashBoardController implements Initializable {
    NotificationService notificationService = new NotificationServiceImpl();
    MedicineService medicineService = new MedicineServiceImpl();
    SalesService salesService = new SalesServiceImpl();
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
    private Button btnSearch;

    @FXML
    private Button btnStaffDashboard;

    @FXML
    private Button btnSuppliers;

    @FXML
    private TableColumn<?, ?> btnSearchBrand;


    @FXML
    private TableColumn<?, ?> colNotificationSupplierId;

    @FXML
    private TableColumn<?, ?> colNotificationDate;

    @FXML
    private TableColumn<Notification, String> colNotificationIssue;

    @FXML
    private TableColumn<Notification, String> colNotificationName;

    @FXML
    private TableColumn<?, ?> colSalesDate;

    @FXML
    private TableColumn<?, ?> colSalesSaleId;

    @FXML
    private TableColumn<?, ?> colSalesTotal;

    @FXML
    private TableColumn<?, ?> colSearchAvailableQuantity;

    @FXML
    private TableColumn<?, ?> colSearchSupplier;

    @FXML
    private TableColumn<?, ?> colSearchExpiryDate;

    @FXML
    private TableColumn<Medicine, String> colSearchName;

    @FXML
    private TableColumn<?, ?> colSearchUnitprice;

    @FXML
    private TableView<Medicine> tblAvailability;

    @FXML
    private TableView<Sale> tblHistory;

    @FXML
    private TableView<Notification> tblNotification;

    @FXML
    private TextField txtSearch;

    @FXML
    void btnDashboardOnAction(ActionEvent event) {
        Stage staffDashboard = new Stage();
        try {
            staffDashboard.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/StaffDashboard.fxml"))));
            staffDashboard.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
        Stage medicine = new Stage();
        try {
            medicine.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Medicine.fxml"))));
            medicine.show();
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
    void btnReportsOnAction(ActionEvent event) {
        Stage report = new Stage();
        try {
            report.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Reports.fxml"))));
            report.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnSalesHistoryOnAction(ActionEvent event) {
        Stage history = new Stage();
        try {
            history.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/SalesHistory.fxml"))));
            history.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnSalesOnAction(ActionEvent event) {
        Stage sales =new Stage();
        try {
            sales.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Sales.fxml"))));
            sales.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) throws SQLException {
        ObservableList<Medicine> list = FXCollections.observableArrayList();

        Medicine medicine = medicineService.searchMedicineByName(txtSearch.getText());
        if (medicine == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("No Medicine Found");
            alert.setContentText("No medicine matches your search.");
            alert.show();
            return;
        }
        list.add(medicine);
        tblAvailability.setItems(list);


    }


    @FXML
    void btnSuppliersOnAction(ActionEvent event) {
        Stage suppliers = new Stage();
        try {
            suppliers.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Suppliers.fxml"))));
            suppliers.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //available table
        colSearchName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSearchSupplier.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colSearchUnitprice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colSearchAvailableQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colSearchExpiryDate.setCellValueFactory(new PropertyValueFactory<>("expireDate"));

        //notification table
        colNotificationName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNotificationSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colNotificationDate.setCellValueFactory(new PropertyValueFactory<>("expireDate"));
        colNotificationIssue.setCellValueFactory(cellData -> {

            LocalDate expire = cellData.getValue().getExpireDate();
            LocalDate today = LocalDate.now();
            String issue;
            if (expire.isBefore(today)) {
                issue = "Recently Expired";
            } else if (!expire.isAfter(today.plusDays(20))) {
                issue = "Expiring Soon";

            } else {
                issue = "Valid";
            }
            return new SimpleStringProperty(issue);
        });

        tblNotification.setItems(notificationService.getAll());


        colSalesSaleId.setCellValueFactory(new PropertyValueFactory<>("saleId"));
        colSalesDate.setCellValueFactory(new PropertyValueFactory<>("saleDate"));
        colSalesTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        tblHistory.setItems(salesService.getAll());


    }
}
