package edu.icet.controller;

import edu.icet.model.dto.SalesHistory;
import edu.icet.service.SalesHistoryService;
import edu.icet.service.impl.SalesHistoryServiceImpl;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SalesHistoryController implements Initializable {

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
    private Button btnSettings;

    @FXML
    private Button btnSuppliers;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colStaff;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TextField txtSearch;

    @FXML
    private TableView<SalesHistory> tblSalesHistory;


    @FXML
    void btnDashboardOnAction(ActionEvent event) {
        Stage dashboard =new Stage();
        try {
            dashboard.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AdminDashBoard.fxml"))));
            dashboard.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {
        Stage logout =new Stage();
        try {
            logout.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/UserLoginForm.fxml"))));
            logout.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnMedicineOnAction(ActionEvent event) {
        Stage medicine =new Stage();
        try {
            medicine.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Medicine.fxml"))));
            medicine.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnNotificationOnAction(ActionEvent event) {
        Stage notification =new Stage();
        try {
            notification.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Notification.fxml"))));
            notification.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnReportsOnAction(ActionEvent event) {
        Stage reports =new Stage();
        try {
            reports.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Reports.fxml"))));
            reports.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnSalesHistoryOnAction(ActionEvent event) {
        Stage history =new Stage();
        try {
            history .setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/SalesHistory.fxml"))));
            history .show();
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
    void btnSettingsOnAction(ActionEvent event) {
        Stage settings =new Stage();
        try {
            settings.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Settings.fxml"))));
            settings.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnSuppliersOnAction(ActionEvent event) {
        Stage suppliers =new Stage();
        try {
            suppliers.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Suppliers.fxml"))));
            suppliers.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    SalesHistoryService salesHistoryService= new SalesHistoryServiceImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colOrderId.setCellValueFactory(new PropertyValueFactory<>("saleId"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        tblSalesHistory.setItems(salesHistoryService.getAll());
    }

    public void btnNameSearchOnAction(ActionEvent actionEvent) {
        ObservableList<SalesHistory> salesHistories = salesHistoryService.searchHistoryByCustomerName(txtSearch.getText());
        tblSalesHistory.setItems(salesHistories);
    }
}
