package com.example.softwareengineerticketproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class WorkerRegisterPageController {

    // PIN to be able to register a worker
    final private int pin = 6769;


    // text fields
    @FXML
    private TextField usernameTextfield;

    @FXML
    private PasswordField passwordPasswordfield;

    @FXML
    private TextField firstNameTextfield;

    @FXML
    private TextField lastNameTextfield;

    @FXML
    private TextField emailTextfield;

    @FXML
    private PasswordField pinPasswordfield;

    // buttons
    @FXML
    private Button cancelButton;

    @FXML
    private Button registerButton;

    @FXML
    private void cancelButtonClicked() throws IOException {
        TicketManagerApplication.setRoot("ClientLoginPage");
    }

}
