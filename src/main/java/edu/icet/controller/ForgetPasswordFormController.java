package edu.icet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ForgetPasswordFormController {

    @FXML
    private Button btnSendMail;

    @FXML
    private TextField txtmail;

    @FXML
    void btnSendMailOnAction(ActionEvent event) {
        Stage sendOTPStage =new Stage();
        try {
            sendOTPStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/SendOTPForm.fxml"))));
            sendOTPStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
