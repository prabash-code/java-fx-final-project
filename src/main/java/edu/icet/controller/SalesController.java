package edu.icet.controller;

import edu.icet.model.dto.CartItem;
import edu.icet.model.dto.Medicine;
import edu.icet.model.dto.Sale;
import edu.icet.service.SalesService;
import edu.icet.service.impl.SalesServiceImpl;
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


public class SalesController implements Initializable {

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnDashbar;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnMedicine;

    @FXML
    private Button btnNotification;

    @FXML
    private Button btnPlaceOrder;

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
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblId;

    @FXML
    private Label lblNameOfMedicine;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblPrice;

    @FXML
    private TextField txtCustomerEmail;

    @FXML
    private TextField txtCustomername;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtItemId;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TableView<CartItem> tblSales;

    SalesService salesService = new SalesServiceImpl();
    ObservableList<CartItem> list = FXCollections.observableArrayList();

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        list.add(new CartItem(
                txtItemId.getText(),
                lblNameOfMedicine.getText(),
                Integer.parseInt(txtQuantity.getText()),
                Double.parseDouble(lblPrice.getText()),
                calculateTotal(lblPrice.getText(), txtQuantity.getText()),
                txtDate.getValue()
        ));

        tblSales.setItems(list);
        clearFields();
        calculateTotalPrice();
    }

    private Double calculateTotal(String price, String quantity) {
        return (Double.parseDouble(price) * Double.parseDouble(quantity));
    }

    private void clearFields() {
        txtItemId.setText("");
        txtQuantity.setText("");
        txtDate.setValue(null);
        lblPrice.setText("");
        lblNameOfMedicine.setText("");

    }

    public void calculateTotalPrice() {
        Double tot = 0.0;
        for (CartItem cartItem : list) {
            tot += cartItem.getTotal();
            lblNetTotal.setText(String.valueOf(tot));
        }
    }


    @FXML
    void btnDashbarOnAction(ActionEvent event) {
        Stage adminDashBoard = new Stage();
        try {
            adminDashBoard.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AdminDashBoard.fxml"))));
            adminDashBoard.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {
        Stage logout = new Stage();
        try {
            logout.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/userLoginForm.fxml"))));
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
    void btnPlaceOrderOnAction(ActionEvent event) {
        salesService.placeOrder(new Sale(
                lblId.getText(),
                LocalDate.now(),
                txtCustomername.getText(),
                txtCustomerEmail.getText()
        ),list);

        Stage sales=new Stage();
        try {
            sales.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Sales.fxml"))));
            sales.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtItemCodeOnAction(ActionEvent event) throws SQLException {
        Medicine medicine = salesService.SerchMedicine(txtItemId.getText());
        if(medicine==null){
            new Alert(Alert.AlertType.ERROR,"Invalid Medicine Code !!").show();
            return;
        }
        lblNameOfMedicine.setText(medicine.getName());
        lblPrice.setText(String.valueOf(medicine.getUnitPrice()));
    }

    //generate order id
    String generateOrderId() {
        String lastOrderId = salesService.getLastOrderId();
        if (lastOrderId == null) {
            return "P001";
        }

        int number = Integer.parseInt(lastOrderId.substring(1));
        number++;
        return "P" + String.format("%03d", number);
    }

    @FXML
    void btnReportsOnAction(ActionEvent event) {
        Stage report = new Stage();
        try {
            report.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Medicine.fxml"))));
            report.show();
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

    }

    @FXML
    void btnSettingsOnAction(ActionEvent event) {
        Stage settings = new Stage();
        try {
            settings.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Settings.fxml"))));
            settings.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) {
        Stage supplier = new Stage();
        try {
            supplier.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Suppliers.fxml"))));
            supplier.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        lblId.setText(generateOrderId());
    }
}
