package edu.icet.controller;

import edu.icet.model.dto.Medicine;
import edu.icet.service.Impl.MedicineServiceImpl;
import edu.icet.service.MedicineService;
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

public class MedicineController implements Initializable {

    MedicineService medicineService =new MedicineServiceImpl();

    @FXML
    private TableColumn<?, ?> colExpDate;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnAdminDashboard;

    @FXML
    private Button btnClear;

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
    private Button btnSalesHistory;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnSetting;

    @FXML
    private Button btnSuppliers;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colBrand;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colManufacDate;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblUser;

    @FXML
    private TableView<Medicine> tblMedicine;

    @FXML
    private TextField txtBrandName;

    @FXML
    private TextField txtCompany;

    @FXML
    private TextField txtExpireDate;

    @FXML
    private TextField txtManufactureDate;

    @FXML
    private TextField txtMedicineName;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        medicineService.addNewMedicine(
                new Medicine(generatedId(),
                txtBrandName.getText(),
                txtMedicineName.getText(),
                txtCompany.getText(),
                Double.parseDouble(txtUnitPrice.getText()),
                Integer.parseInt(txtQuantity.getText()),
                LocalDate.parse(txtManufactureDate.getText()),
                LocalDate.parse(txtExpireDate.getText()))

        );
        loadTable();
        btnClearOnAction(event);

    }

    private String generatedId() {
        String lastId = medicineService.generateMedicineId();
        if(lastId==null){
            return "m001";
        }

        int number=Integer.parseInt(lastId.substring(1));
        number++;

        return "m"+String.format("%03d",number);
    }

    @FXML
    void btnAdminDashbooardOnAcation(ActionEvent event) {
        Stage adminDashBoard=new Stage();
        try {
            adminDashBoard.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AdminDashBoard.fxml"))));
            adminDashBoard.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtBrandName.setText("");
        txtMedicineName.setText("");
        txtCompany.setText("");
        txtUnitPrice.setText("");
        txtQuantity.setText("");
        txtExpireDate.setText("");
        txtManufactureDate.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        medicineService.deleteMedicine(txtMedicineName.getText());
        loadTable();
        btnClearOnAction(event);

    }

    @FXML
    void btnLogout(ActionEvent event) {
        Stage logout =new Stage();
        try {
            logout.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/userLoginForm.fxml"))));
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
//Search Medicine
    @FXML
    void btnSearchOnAction(ActionEvent event) {

        try {
            Medicine medicine = medicineService.searchMedicine(txtSearch.getText());
            ObservableList<Medicine> list = FXCollections.observableArrayList();
            if (medicine == null) {
                new Alert(Alert.AlertType.ERROR, "No medicine found!").show();
                return;
            }
            list.add(new Medicine(
                    medicine.getMedicineId(),
                    medicine.getBrand(),
                    medicine.getName(),
                    medicine.getSupplierId(),
                    medicine.getUnitPrice(),
                    medicine.getQuantity(),
                    medicine.getManufactureDate(),
                    medicine.getExpireDate()));
            tblMedicine.setItems(list);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnSettingOnAction(ActionEvent event) {
        Stage settings =new Stage();
        try {
            settings.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Settings.fxml"))));
            settings.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSuppliyersOnAction(ActionEvent event) {
        Stage supplier =new Stage();
        try {
            supplier.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Suppliers.fxml"))));
            supplier.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        medicineService.UpdateMedicine(
                new Medicine(generatedId(),
                        txtBrandName.getText(),
                        txtMedicineName.getText(),
                        txtCompany.getText(),
                        Double.parseDouble(txtUnitPrice.getText()),
                        Integer.parseInt(txtQuantity.getText()),
                        LocalDate.parse(txtManufactureDate.getText()),
                        LocalDate.parse(txtExpireDate.getText()))

        );
        loadTable();
        btnClearOnAction(event);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("medicineId"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colManufacDate.setCellValueFactory(new PropertyValueFactory<>("manufactureDate"));
        colExpDate.setCellValueFactory(new PropertyValueFactory<>("expireDate"));

        loadTable();

        tblMedicine.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtBrandName.setText(newSelection.getBrand());
                txtMedicineName.setText(newSelection.getName());
                txtCompany.setText(newSelection.getSupplierId());
                txtUnitPrice.setText(String.valueOf(newSelection.getUnitPrice()));
                txtQuantity.setText(String.valueOf(newSelection.getQuantity()));
                txtManufactureDate.setText(newSelection.getManufactureDate().toString());
                txtExpireDate.setText(newSelection.getExpireDate().toString());
            }
        });
    }

    private void loadTable(){
        tblMedicine.setItems(medicineService.getAll());
    }


}
