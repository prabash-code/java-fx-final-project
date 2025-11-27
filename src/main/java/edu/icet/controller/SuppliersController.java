package edu.icet.controller;


import edu.icet.model.dto.Supplier;
import edu.icet.service.impl.SupplierServiceImpl;
import edu.icet.service.SupplierService;
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
import java.util.ResourceBundle;

public class SuppliersController implements Initializable {
    SupplierService supplierService=new SupplierServiceImpl();
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnDelete;

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
    private Button btnSetting;

    @FXML
    private Button btnSuppliers;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colCompany;

    @FXML
    private TableColumn<?, ?> colContactNo;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<Supplier> supplierTable;

    @FXML
    private TextField txtCompanyName;

    @FXML
    private TextField txtContactNumber;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private Label lblSupplierId;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        supplierService.addNewSupplier(
                new Supplier(generatedSupplierId(),
                        txtName.getText(),
                        txtCompanyName.getText(),
                        txtEmail.getText(),
                        txtContactNumber.getText()
        ));
        loadTable();
        btnClearOnAction(event);

    }

    private String generatedSupplierId() {
        String lastId = supplierService.generateSupplierId();
        if(lastId==null){
            return "S001";
        }
        int number=Integer.parseInt(lastId.substring(1));
        number++;
        return "S"+String.format("%03d",number);
    }



    @FXML
    void btnClearOnAction(ActionEvent event) {
        lblSupplierId.setText("");
        txtCompanyName.setText("");
        txtEmail.setText("");
        txtName.setText("");
        txtContactNumber.setText("");

    }

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
    void btnDeleteOnAction(ActionEvent event) {
        supplierService.deleteSupplier(lblSupplierId.getText());
        loadTable();
        btnClearOnAction(event);

    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {
        Stage logOut =new Stage();
        try {
            logOut.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/UserLoginForm.fxml"))));
            logOut.show();
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
        Stage salesHistory =new Stage();
        try {
            salesHistory.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/SalesHistory.fxml"))));
            salesHistory.show();
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
    void btnSettingOnAction(ActionEvent event) {
        Stage setting=new Stage();
        try {
            setting.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Settings.fxml"))));
            setting.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSuppliersOnAction(ActionEvent event) {
        Stage suppliers=new Stage();
        try {
            suppliers.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Suppliers.fxml"))));
            suppliers.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        supplierService.updateSupplier(new Supplier(
                lblSupplierId.getText(),
                txtName.getText(),
                txtCompanyName.getText(),
                txtEmail.getText(),
                txtContactNumber.getText()

                ));
        loadTable();
        btnClearOnAction(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("phone"));

        loadTable();

        supplierTable.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection)->{
            if (newSelection != null) {
                lblSupplierId.setText(newSelection.getSupplierId());
                txtName.setText(newSelection.getName());
                txtEmail.setText(newSelection.getEmail());
                txtCompanyName.setText(newSelection.getCompany());
                txtContactNumber.setText(newSelection.getPhone() );
            }
        });
    }
    public void loadTable(){
        supplierTable.setItems(supplierService.getAll());
    }
}
