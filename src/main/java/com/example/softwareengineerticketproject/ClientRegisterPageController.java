package com.example.softwareengineerticketproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

/*
This class is the controller for the client register page, it provides the ability for it to function. It contains these
methods:
    * cancelButtonClicked - allows cancel button to function

*/

public class ClientRegisterPageController {

    // text fields
    @FXML
    private TextField usernameTextfield;

    @FXML
    private PasswordField passwordTextfield;

    @FXML
    private TextField firstNameTextfield;

    @FXML
    private TextField lastNameTextfield;

    @FXML
    private TextField emailTextfield;

    // buttons
    @FXML
    private Button cancelButton;

    @FXML
    private Button registerButton;

    // this method is activated when the cancel button is clicked and redirects to the ClientLoginPage.
    @FXML
    private void cancelButtonClicked() throws IOException{
        TicketManagerApplication.setRoot("ClientLoginPage");
    }

}
