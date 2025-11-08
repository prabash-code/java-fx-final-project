package edu.icet.controller;

import edu.icet.service.Impl.UserLoginServiceImpl;
import edu.icet.service.UserLoginService;
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

public class userLoginFormController {
    UserLoginService userLoginService=new UserLoginServiceImpl();

    @FXML
    private Button backToHomebtn;

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtEmailBtn;

    @FXML
    private PasswordField txtPasswordBtn;

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
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Registered !!");
            alert.setContentText("Welcome to HealthNetPharmacy ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void checkPassword(){

        userLoginService.checkPassword(txtEmailBtn.getText(),txtPasswordBtn.getText());
    }

}
