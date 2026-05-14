package com.example.softwareengineerticketproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class WorkerHomePageController {

    // assigns the username to label when this page starts
    public void initialize(){
        usernameLabel.setText(sessionUsername);
    }

    // labels
    @FXML
    private Label usernameLabel;

    // Buttons
    @FXML
    private Button logoutButton;

    // session user variable
    private static String sessionUsername;

    public static void setUsername(String username) {
        sessionUsername = username;
    }

    // this method allows the logout button to work. it wipes the session variable and redirects to worker login page
    @FXML
    private void logoutButtonClicked() throws IOException{
        sessionUsername = null;
        TicketManagerApplication.setRoot("WorkerLoginPage");
    }

}
