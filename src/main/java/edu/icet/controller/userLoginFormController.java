package edu.icet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class userLoginFormController {

    @FXML
    private Button backToHomebtn;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnRegister;

    @FXML
    void backToHomeBtnOnAction(ActionEvent event) {

        Stage backToHome=new Stage();
        try {
            backToHome.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/HomePage.fxml"))));
            backToHome.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void loginOnAction(ActionEvent event) {

    }

    @FXML
    void registerOnAction(ActionEvent event) {
        Stage registerStage= new Stage();
        try {
            registerStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/RegisterPage.fxml"))));
            registerStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
