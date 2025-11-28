package edu.icet.controller;

import edu.icet.service.ForgetEmailSendService;
import edu.icet.service.impl.ForgetEmailSendServiceImpl;
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

public class ForgetEmailController {
    ForgetEmailSendService forgetEmailSendService = new ForgetEmailSendServiceImpl();
    private int otp;

    @FXML
    private Button backToHomebtn;

    @FXML
    private TextField txtOTP;

    @FXML
    private Button btnForgotPassword;

    @FXML
    private Button btnVerify;

    @FXML
    private Button btnRegister;

    @FXML
    private TextField txtEmailBtn;

    @FXML
    private PasswordField txtPasswordBtn;

    @FXML
    void backToHomeBtnOnAction(ActionEvent event) {
        Stage home = new Stage();
        try {
            home.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/UserLoginForm.fxml"))));
            home.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    boolean btnForgotPasswordOnAction(ActionEvent event) {
        String mailReceiver = txtEmailBtn.getText();
        String message = "Your OTP is " + generateOTP();
        String subject = " OTP from HealthNet ";

       return forgetEmailSendService.sendOTP(mailReceiver, message, subject);

    }


    @FXML
    void verifyOnAction(ActionEvent event) {
        boolean isSend = btnForgotPasswordOnAction(event);
        if (isSend==true) {
            if (generateOTP().equals(txtOTP.getText())) {
                try {
                    Stage addNewPassword=new Stage();
                    addNewPassword.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ResetPassword.fxml"))));
                    addNewPassword.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }else if (isSend==false) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Wrong OTP");
                alert.setContentText("your OTP is wrong try again..");
                alert.setTitle("OTP Incorrect");
                alert.showAndWait();
            }


        } else if (isSend==false) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Message Not Send");
            alert.setContentText("your message not send try again..");
            alert.setTitle("Try Again!!");
            alert.showAndWait();


        }

    }

    @FXML
    void registerOnAction(ActionEvent event) {

    }

    public String generateOTP() {
        otp = (int) (Math.random() * 90000 + 100000);
        return String.valueOf(otp);
    }
}
