package edu.icet.controller;

import edu.icet.model.dto.User;
import edu.icet.service.Impl.UserRegistrationServiceImpl;
import edu.icet.service.UserRegistrationService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterPageController implements Initializable {



    @FXML
    private Button btnUserLogin;

    @FXML
    private ComboBox<String> cmbRole;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

   UserRegistrationService userRegistrationService=new UserRegistrationServiceImpl();

    private int adminCount=0;
    private int customerCount=0;
    private int staffCount=0;

    @FXML
    void btnUserLoginOnAction(ActionEvent event) {
        Stage userLoginStage =new Stage();

        userRegistrationService.RegisterNewUser(new User(getNewId(),
                txtUserName.getText(),
                txtEmail.getText(),
                txtPassword.getText(),
                cmbRole.getValue()
        ));

//        try {
//         userLoginStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/userLoginForm.fxml"))));
//           userLoginStage.show();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }


    private String getNewId() {
        String role=cmbRole.getValue();
        if(role.equals("Admin")){
            adminCount++;
            return String.format("A%03d",adminCount);
        }
        else if(role.equals("Staff")){
            staffCount++;
            return String.format("S%03d",staffCount);
        }
        else{
            customerCount++;
            return String.format("C%03d",customerCount);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> userType= FXCollections.observableArrayList(
                "Admin",
                    "Staff",
                    "Customer"

        );

        cmbRole.setItems(userType);

    }

}
