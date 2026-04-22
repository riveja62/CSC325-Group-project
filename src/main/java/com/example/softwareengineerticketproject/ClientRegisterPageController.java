package com.example.softwareengineerticketproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ClientRegisterPageController {

    // text fields
    @FXML
    private TextField usernameTextfield;

    @FXML
    private TextField passwordTextfield;

    @FXML
    private TextField firstNameTextfield;

    @FXML
    private TextField lastNameTextfield;

    @FXML
    private TextField phoneNumberTextfield;

    // buttons
    @FXML
    private Button cancelButton;

    @FXML
    private Button registerButton;

    @FXML
    private void cancelButtonClicked() throws IOException{
        TicketManagerApplication.setRoot("ClientLoginPage");
    }

}
