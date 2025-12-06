package edu.icet.controller;

import edu.icet.service.ChangeDetailsService;
import edu.icet.service.impl.ChangeDetailsServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class AboutUsController implements Initializable {
ChangeDetailsService changeDetailsService=new ChangeDetailsServiceImpl();
    @FXML
    private TextArea txtArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtArea.setText(changeDetailsService.getDetails().getAbout());
    }
}
