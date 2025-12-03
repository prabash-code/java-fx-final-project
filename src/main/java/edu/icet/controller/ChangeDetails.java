package edu.icet.controller;

import edu.icet.model.dto.ContactDetails;
import edu.icet.service.ChangeDetailsService;
import edu.icet.service.impl.ChangeDetailsServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChangeDetails implements Initializable {
    ChangeDetailsService changeDetailsService=new ChangeDetailsServiceImpl();

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSetDetails;

    @FXML
    private TextArea txtAbout;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContactNumber;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNetwork;

    @FXML
    void btnBackOnAction(ActionEvent event) {
        Stage back= new Stage();
        try {
            back.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Home.fxml"))));
            back.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnSetDetailsOnAction(ActionEvent event) {

        String number = txtContactNumber.getText();
        String email= txtEmail.getText();
        String network = txtNetwork.getText();
        String address = txtAddress.getText();
        String about = txtAbout.getText();

        changeDetailsService.updateDetails(number,email,network,address,about);
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Updated!");
        alert.setContentText("contact details added sucessfully!");
        alert.showAndWait();



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ContactDetails details = changeDetailsService.getDetails();
        txtAddress.setText(details.getAddress());
        txtEmail.setText(details.getEmail());
        txtNetwork.setText(details.getNetwork());
        txtContactNumber.setText(details.getNumber());
        txtAbout.setText(details.getAbout());



    }
}
