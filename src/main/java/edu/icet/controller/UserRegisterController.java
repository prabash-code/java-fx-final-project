package edu.icet.controller;

import edu.icet.model.dto.User;
import edu.icet.service.impl.UserRegistrationServiceImpl;
import edu.icet.service.UserRegistrationService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserRegisterController implements Initializable {


    @FXML
    private Button btnBack;

    @FXML
    private ComboBox<String> cmbRole;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    UserRegistrationService userRegistrationService = new UserRegistrationServiceImpl();

    private int adminCount = 0;
    private int customerCount = 0;
    private int staffCount = 0;

    @FXML
    void btnUserLoginOnAction(ActionEvent event) {
        Stage userLoginStage = new Stage();

        userRegistrationService.RegisterNewUser(new User(getNewId(), txtUserName.getText(), txtEmail.getText(), txtPassword.getText(), cmbRole.getValue()));
    }

    @FXML
    void btnBackOnAction(ActionEvent event) {
        Stage log = new Stage();
        try {
            log.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/UserLoginForm.fxml"))));
            log.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    private String getNewId() {
        String role = cmbRole.getValue();
        return userRegistrationService.generateNewUserId(role.substring(0, 1));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> userType = FXCollections.observableArrayList("Admin", "Staff");

        cmbRole.setItems(userType);

    }

}
