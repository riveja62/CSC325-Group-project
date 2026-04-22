package com.example.softwareengineerticketproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ClientLoginPageController {

    // buttons
    @FXML
    private Button goBackButton;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    // text fields
    @FXML
    private TextField usernameTextfield;

    @FXML
    private TextField passwordTextfield;

    // this method is activated when the go back button is clicked and redirects to the who are you page.
    @FXML
    private void goBackButtonClicked() throws IOException{
        TicketManagerApplication.setRoot("UserIdentifierPage");
    }
}
