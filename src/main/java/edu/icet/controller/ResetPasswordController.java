package edu.icet.controller;

import edu.icet.service.ResetPasswordService;
import edu.icet.service.impl.ResetPasswordServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ResetPasswordController {

    @FXML
    private Button backToHomebtn;

    @FXML
    private TextField txtEmail;

    @FXML
    private Button btnConformPassword;

    @FXML
    private Button btnRegister;

    @FXML
    private PasswordField passwordFirstTime;

    @FXML
    private PasswordField passwordSecondTime;

    @FXML
    void backToHomeBtnOnAction(ActionEvent event) {

        try {
            Stage homeStage=new Stage();
            homeStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Home.fxml"))));
            homeStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnConformPasswordOnAction(ActionEvent event) {
        ResetPasswordService resetPasswordService=new ResetPasswordServiceImpl();

         String pw1=passwordFirstTime.getText();
         String pw2=passwordSecondTime.getText();
         String email=txtEmail.getText();
         resetPasswordService.setNewPassword(pw1,pw2,email);

    }

    @FXML
    void registerOnAction(ActionEvent event) {

    }

}

