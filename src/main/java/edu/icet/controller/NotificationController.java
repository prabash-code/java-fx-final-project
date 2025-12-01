package edu.icet.controller;


import edu.icet.model.dto.Notification;
import edu.icet.service.NotificationService;
import edu.icet.service.impl.NotificationServiceImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class NotificationController implements Initializable {
    NotificationService notificationService=new NotificationServiceImpl();
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
    private Button btnSupplier;

    @FXML
    private TableColumn<?, ?> colCompany;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colMedicineId;

    @FXML
    private TableColumn<?, ?> colMedicineName;

    @FXML
    private TableColumn<?, ?> colSupplierMail;

    @FXML
    private TableColumn<Notification, String> colTypeOfIssue;


    @FXML
    private TableColumn<Notification,String> colQtymedicineName;

    @FXML
    private TableColumn<?, ?> colQtyMedicineId;

    @FXML
    private TableColumn<?, ?> colQtySuppliers;

    @FXML
    private TableColumn<?, ?> colQtySuppliersMail;

    @FXML
    private TableColumn<Notification,String> colQuantity;



    @FXML
    private ImageView lblUser;

    @FXML
    private TableView<Notification> tblNotification;


    @FXML
    private TableView<Notification> tblLowStockNotification;

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
    void btnReportsOnAction(ActionEvent event) {
        Stage reports=new Stage();
        try {
            reports.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Reports.fxml"))));
            reports.show();
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
    void btnSettingsOnAction(ActionEvent event) {
        Stage setting=new Stage();
        try {
            setting.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Settings.fxml"))));
            setting.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) {
        Stage supplier=new Stage();
        try {
            supplier.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Suppliers.fxml"))));
            supplier.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        colMedicineId.setCellValueFactory(new PropertyValueFactory<>("medicineId"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colMedicineName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("expireDate"));
        colSupplierMail.setCellValueFactory(new PropertyValueFactory<>("email"));

        colTypeOfIssue.setCellValueFactory(cellData-> {

            LocalDate expire = cellData.getValue().getExpireDate();
            LocalDate today=LocalDate.now();
                    String issue;
                    if(expire.isBefore(today)){
                        issue="Recently Expired";
                    } else if (!expire.isAfter(today.plusDays(20))) {
                        issue="Expiring Soon";

                    }else{
                        issue="Valid";
                    }
                    return new SimpleStringProperty(issue);
                });

     tblNotification.setItems(notificationService.getAll());

     colQtyMedicineId.setCellValueFactory(new PropertyValueFactory<>("medicineId"));
     colQtymedicineName.setCellValueFactory(new PropertyValueFactory<>("name"));
     colQtySuppliers.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
     colQtySuppliersMail.setCellValueFactory(new PropertyValueFactory<>("email"));
     colQuantity.setCellValueFactory( cellValue->{
            int qty= cellValue.getValue().getQty();
            String note;
            if(qty<=100){
                note="Stock is low";
            }else if(qty==0){
                note="Out of stock";

         }else{
                note="Available";
            }
         return new SimpleStringProperty(note);

     });

     tblLowStockNotification();
    }

    private void tblLowStockNotification() {
        tblLowStockNotification.setItems(notificationService.getQuantityUpdates());
    }
}
