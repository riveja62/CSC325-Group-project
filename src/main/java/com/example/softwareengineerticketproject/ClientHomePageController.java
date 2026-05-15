package com.example.softwareengineerticketproject;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class ClientHomePageController {

    // assigns the username to label when this page starts and populates the choice boxes
    public void initialize(){
        // assigns the label
        usernameLabel.setText(sessionUsername);

        // populates device choice box
        deviceChoiceBox.getItems().addAll("Phone", "Tablet", "Computer", "Other");

        // populates issue choice box
        issueChoiceBox.getItems().addAll("Lags", "Freezes", "Crashes", "Other");

        // sets default choices
        deviceChoiceBox.setValue("Device");
        issueChoiceBox.setValue("Issue");
    }

    // choice boxes
    @FXML
    private ChoiceBox<String> deviceChoiceBox;

    @FXML
    private ChoiceBox<String> issueChoiceBox;


    // text fields
    @FXML
    private TextField subjectTextField;

    // text area
    @FXML
    private TextArea descriptionTextArea;

    // labels
    @FXML
    private Label usernameLabel;

    // Buttons
    @FXML
    private Button logoutButton;

    @FXML
    private Button submitTicketButton;

    // session user variable
    private static String sessionUsername;

    public static void setUsername(String username) {
        sessionUsername = username;
    }

    // this method allows the logout button to work. it wipes the session variable and redirects to worker login page
    @FXML
    private void logoutButtonClicked() throws IOException {
        sessionUsername = null;
        TicketManagerApplication.setRoot("ClientLoginPage");
    }

}
