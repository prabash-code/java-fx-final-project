package edu.icet.controller;

import edu.icet.service.impl.UserLoginServiceImpl;
import edu.icet.service.UserLoginService;
import edu.icet.util.Security;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class UserLoginFormController {

    private UserLoginService userLoginService = new UserLoginServiceImpl();

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
    private Button btnForgotPassword;
    @FXML
    private CheckBox checkBox;
    @FXML
    private TextField txtPasswordField;

    @FXML
    void backToHomeBtnOnAction(ActionEvent event) {
        try {
            Stage backToHome = new Stage();
            backToHome.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/HomePage.fxml"))));
            backToHome.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnForgotPasswordOnAction(ActionEvent event) {
        try {
            Stage forgetpw = new Stage();
            forgetpw.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ForgetForm.fxml"))));
            forgetpw.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void checkBoxOnAction(ActionEvent event) {

        txtPasswordBtn.setManaged(!checkBox.isSelected());
        txtPasswordField.setManaged(checkBox.isSelected());
        txtPasswordField.setVisible(false);

        if (checkBox.isSelected()) {
            txtPasswordField.setText(txtPasswordBtn.getText());
            txtPasswordField.setVisible(true);
            txtPasswordBtn.setVisible(false);
        }else{
            txtPasswordBtn.setText(txtPasswordField.getText());
            txtPasswordField.setVisible(false);
            txtPasswordBtn.setVisible(true);

        }
    }

    @FXML
    void loginOnAction(ActionEvent event) {
        String email = txtEmailBtn.getText();
        String password = txtPasswordBtn.getText();

        // 1. Get stored hash
        String hashCode = userLoginService.checkPassword(email);

        if (hashCode == null) {
            new Alert(Alert.AlertType.ERROR, "Email not registered!").show();
            return;
        }

        // 2. Verify password
        boolean isCorrect = false;
        try {
            isCorrect = Security.verifyPassword(password, hashCode);
        } catch (IllegalArgumentException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid stored password hash!").show();
            return;
        }

        if (!isCorrect) {
            new Alert(Alert.AlertType.ERROR, "Incorrect password!").show();
            return;
        }

        // 3. Redirect based on role
        String role = userLoginService.checkUserRole(email);
        Stage dashboard = new Stage();
        try {
            if ("Admin".equals(role)) {
                dashboard.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AdminDashBoard.fxml"))));
            } else if ("Staff".equals(role)) {
                dashboard.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/StaffDashboard.fxml"))));

            } else {
                new Alert(Alert.AlertType.ERROR, "Role not found!").show();
                return;
            }
            dashboard.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void registerOnAction(ActionEvent event) {
        try {
            Stage registerStage = new Stage();
            registerStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/RegisterPage.fxml"))));
            registerStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnBackToHomeOnAction(ActionEvent actionEvent) {
        try {
            Stage back = new Stage();
            back.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Home.fxml"))));
            back.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
