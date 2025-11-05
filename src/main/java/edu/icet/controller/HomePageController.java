package edu.icet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePageController {

    @FXML
    private Button btnAbout;

    @FXML
    private Button btnContactUs;

    @FXML
    private Button btnCustomerPortral;

    @FXML
    private Button btnLogin;

    @FXML
    private ImageView myImageView;

    @FXML
    void btnAboutOnAction(ActionEvent event) {
        Stage aboutStage=new Stage();


    }

    @FXML
    void btnContactUsOnAction(ActionEvent event) {
        Stage contactUs=new Stage();

    }

    @FXML
    void btnCustomerPortalOnAction(ActionEvent event) {
        Stage customerPortral =new Stage();


    }

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        Stage loginStage =new Stage();
        try {
            loginStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/userLoginForm.fxml"))));
            loginStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
