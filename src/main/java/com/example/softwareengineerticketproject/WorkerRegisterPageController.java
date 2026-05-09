package com.example.softwareengineerticketproject;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
This class is the controller for the worker register page, it provides the ability for it to function. It contains these
methods:
    * initialize - ensures only numbers are inputted into the pin field
    * cancelButtonClicked - allows cancel button to function
    * registerButtonClicked - allows the register button to function
    * registerUser - this method registers a user and adds them to firestore

*/

public class WorkerRegisterPageController {


    // this initialize method makes it so the pin field ONLY accepts numbers
    public void initialize() {
        pinPasswordfield.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("\\d*")) {
                return change;
            }
            return null;
        }));
    }


    // hardcoded PIN to be able to register a worker
    final private int pin = 6769;

    // labels
    @FXML
    private Label errorLabel;

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
        TicketManagerApplication.setRoot("WorkerLoginPage");
    }

    /*
    this method is activated when the register button is clicked. it tries to register a user and if successful it
    redirects to the login page
 */
    @FXML
    private void registerButtonClicked() throws IOException{
        if(registerUser()) {
            TicketManagerApplication.setRoot("WorkerLoginPage");
        }
    }


    /*
this method performs 5 actions:
    - makes sure no field is empty
    - checks if correct pin was inputted
    - checks if username is taken
    - adds user to authentication
    - adds user and their info to firestore
 */
    public boolean registerUser() {

        if (usernameTextfield.getText().isEmpty() ||
                passwordPasswordfield.getText().isEmpty() ||
                firstNameTextfield.getText().isEmpty() ||
                lastNameTextfield.getText().isEmpty() ||
                emailTextfield.getText().isEmpty() ||
                pinPasswordfield.getText().isEmpty()) {

            errorLabel.setText("All fields must be filled out");
            return false;
        }


        // this makes sure the correct pin was entered
        int enteredPin;

        try {
            enteredPin = Integer.parseInt(pinPasswordfield.getText());
        } catch (NumberFormatException e) {
            errorLabel.setText("PIN must be numbers only");
            return false;
        }

        if (enteredPin != pin) {
            errorLabel.setText("Invalid PIN");
            return false;
        }


        // this makes sure a username isn't already taken
        ApiFuture<QuerySnapshot> future = TicketManagerApplication.fstore
                .collection("Workers")
                .whereEqualTo("username", usernameTextfield.getText())
                .get();

        QuerySnapshot snapshot;
        try {
            snapshot = future.get();
        } catch (Exception e) {
            errorLabel.setText("Error checking username availability");
            return false;
        }

        if (!snapshot.isEmpty()) {
            errorLabel.setText("Username already taken!");
            return false;
        }

        // adds user to authentication
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(emailTextfield.getText())
                .setEmailVerified(false)
                .setPassword(passwordPasswordfield.getText())
                .setDisplayName(usernameTextfield.getText())
                .setDisabled(false);

        UserRecord userRecord;
        try {
            userRecord = TicketManagerApplication.fauth.createUser(request);
            System.out.println("Successfully created new user with Firebase Uid: " + userRecord.getUid()
                    + " check Firebase > Authentication > Users tab");

        } catch (FirebaseAuthException ex) {
            System.out.println("Error creating a new user in the firebase");
            errorLabel.setText("Error: Unable to register user!");
            return false;
        }

        // adds user to firestore so password and other information is saved
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("ID", userRecord.getUid());
        userMap.put("firstName", firstNameTextfield.getText());
        userMap.put("lastName", lastNameTextfield.getText());
        userMap.put("username", usernameTextfield.getText());
        userMap.put("password", passwordPasswordfield.getText());
        userMap.put("email", emailTextfield.getText());


        ApiFuture<WriteResult> result =
                TicketManagerApplication.fstore.collection("Workers")
                        .document(userRecord.getUid())
                        .set(userMap);
        return true;
    }


}
