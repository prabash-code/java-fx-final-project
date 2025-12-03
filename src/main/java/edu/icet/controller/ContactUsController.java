package edu.icet.controller;

import edu.icet.model.dto.ContactDetails;
import edu.icet.service.ChangeDetailsService;
import edu.icet.service.impl.ChangeDetailsServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ContactUsController implements Initializable {
ChangeDetailsService changeDetailsService=new ChangeDetailsServiceImpl();
    @FXML
    private Label lblAddress;

    @FXML
    private Label lblContactNumber;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblNetwork;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ContactDetails details = changeDetailsService.getDetails();
        lblAddress.setText(details.getAddress());
        lblEmail.setText(details.getEmail());
        lblNetwork.setText(details.getNetwork());
        lblContactNumber.setText(details.getNumber());
    }
}
