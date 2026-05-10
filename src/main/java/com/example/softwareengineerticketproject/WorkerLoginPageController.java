package com.example.softwareengineerticketproject;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

/*
This class is the controller for the worker login page, it provides the ability for it to function. It contains these
methods:
    * goBackButtonClicked - allows go back button to function
    * registerButtonClicked - allows register button to function

*/

public class WorkerLoginPageController {

    // labels
    @FXML
    private Label errorLabel;

    // buttons
    @FXML
    private Button goBackButton;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    //text fields
    @FXML
    private TextField usernameTextfield;

    @FXML
    private PasswordField passwordTextfield;

    // this method is activated when the go back button is clicked and redirects to the who are you page.
    @FXML
    private void goBackButtonClicked() throws IOException {
        TicketManagerApplication.setRoot("UserIdentifierPage");
    }

    @FXML
    private void registerButtonClicked() throws IOException{
        TicketManagerApplication.setRoot("WorkerRegisterPage");
    }

    @FXML
    private void loginButtonClicked() throws IOException{
        if(login()){
            TicketManagerApplication.setRoot("WorkerHomePage");
        }
    }

    private boolean login() throws IOException {

        if (usernameTextfield.getText().isEmpty() ||
                passwordTextfield.getText().isEmpty()) {

            errorLabel.setText("All fields must be filled out");
            return false;
        }


        String username = usernameTextfield.getText();
        String password = passwordTextfield.getText();

        try {
            // Query Firestore for username
            ApiFuture<QuerySnapshot> future = TicketManagerApplication.fstore
                    .collection("Workers")
                    .whereEqualTo("username", username)
                    .get();

            QuerySnapshot snapshot = future.get();

            if (snapshot.isEmpty()) {
                errorLabel.setText("User not found");
                return false;
            }

            DocumentSnapshot doc = snapshot.getDocuments().get(0);

            String storedPassword = doc.getString("password");

            if (storedPassword.equals(password)) {
                System.out.println("Login successful!");
            } else {
                errorLabel.setText("Incorrect password");
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            errorLabel.setText("Login error");
            return false;
        }
        return true;
    }


}
