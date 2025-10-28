package edu.icet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {

    public PasswordField passwordField;
    @FXML
    private Button btnForgotPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnRegister;

    @FXML
    private CheckBox checkBoxLog;

    @FXML
    private PasswordField password;

    @FXML
    private TextField txtUserName;

    @FXML
    void checkBoxLoginOnAction(ActionEvent event) {

    }

    @FXML
    void forgotPasswordOnAction(ActionEvent event) {
        Stage forgotPasswordStage= new Stage();
        try {
            forgotPasswordStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ForgetPassword.xml.fxml"))));
            forgotPasswordStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void loginOnAction(ActionEvent event) {
        String userName=txtUserName.getText();
        String password=passwordField.getText();

    }

    @FXML
    void passwordOnAction(ActionEvent event) {

    }

    @FXML
    void registerOnAction(ActionEvent event) {
        Stage register =new Stage();

        try {
            register.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/CreateAccount.fxml"))));
            register.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void userNameOnAction(ActionEvent event) {

    }

}


