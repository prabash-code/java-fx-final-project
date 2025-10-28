
package edu.icet.controller;

import edu.icet.model.dto.CustomerLoginDetails;
import edu.icet.service.CustomerLoginDetailsService;
import edu.icet.service.CustomerLoginDetailsServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CreateAccountFormController {


    @FXML
    private Button btnCreateAccount;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    void createAccountOnAction(ActionEvent event) {
        CustomerLoginDetailsService customerLoginDetailsService=new CustomerLoginDetailsServiceImpl();

        CustomerLoginDetails customerLoginDetails = new CustomerLoginDetails(txtUserName.getText(), txtEmail.getText(), txtPassword.getText());
        customerLoginDetailsService.addCustomerLogingDetails(customerLoginDetails);



    }
    @FXML
    void emailOnAction(ActionEvent event) {

    }

    @FXML
    void passwordOnAction(ActionEvent event) {

    }

    @FXML
    void userNameOnAction(ActionEvent event) {

    }

}

