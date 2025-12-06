package edu.icet.service.impl;

import edu.icet.model.dto.User;
import edu.icet.service.ResetPasswordService;
import edu.icet.service.UserRegistrationService;
import edu.icet.util.Security;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class ResetPasswordServiceImpl implements ResetPasswordService {
    UserRegistrationService userRegistrationService = new UserRegistrationServiceImpl();

    @Override
    public void setNewPassword(String pw1, String pw2, String email) {
        if (pw1.equals(pw2)) {

            String hashPassword = Security.hashPassword(pw1);
            userRegistrationService.updateUserPassword(email, hashPassword);
            try {
                Stage loginStage = new Stage();
                loginStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/UserLoginForm.fxml"))));
                loginStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Passwords Not Matching");
            alert.setContentText("Password are not same.Try Again!!");
            alert.showAndWait();
        }
    }
}
