package edu.icet.controller;

import edu.icet.service.ForgetEmailSendService;
import edu.icet.service.impl.ForgetEmailSendServiceImpl;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
    boolean btnForgotPasswordOnAction(ActionEvent event)  {
         otp= Integer.parseInt(generateOTP());
        String mailReceiver = txtEmailBtn.getText();
        String message = "Your OTP is " + otp;
        String subject = " OTP from HealthNet ";

        boolean mailSend = forgetEmailSendService.sendOTP(mailReceiver, message, subject);
        if(mailSend==true){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("OTP Sent Successfully");
            alert.setContentText("Please check your email for the OTP.");
            alert.showAndWait();

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Failed to Send Email");
            alert.setContentText("Please check your internet or email address and try again.");
            alert.showAndWait();
        }
        return mailSend;
    }


    @FXML
    void verifyOnAction(ActionEvent event) {

    String userOtp=txtOTP.getText();
        if (userOtp.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("OTP Required");
            alert.setContentText("Please enter the OTP sent to your email.");
            alert.showAndWait();
            return;
        }

        if (String.valueOf(otp).equals(userOtp)) {
            try {
                Stage addNewPassword = new Stage();
                addNewPassword.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ResetPassword.fxml"))));
                addNewPassword.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Wrong OTP");
            alert.setContentText("your OTP is wrong try again..");
            alert.setTitle("OTP Incorrect");
            alert.showAndWait();
        }
    }

    @FXML
    void registerOnAction(ActionEvent event) {

        try {
            Stage registerForm=new Stage();
            registerForm.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/RegisterPage.fxml"))));
            registerForm.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String generateOTP() {
        otp = (int) (Math.random() * 90000 + 100000);
        return String.valueOf(otp);
    }
}
