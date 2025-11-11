package edu.icet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePageController {

    @FXML
    private Button btnAbout;

    @FXML
    private Button btnContact;

    @FXML
    private Button btnLogin;

    @FXML
    void btnAboutOnAction(ActionEvent event) {
        Stage about =new Stage();
        try {
            about.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/About.fxml"))));
            about.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnContactOnAction(ActionEvent event) {
        Stage contactUs= new Stage();
        try {
            contactUs.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ContactUs.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void loginButtonOnAction(ActionEvent event) {
        Stage login =new Stage();
        try {
            login.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/userLoginForm.fxml"))));
            login.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
