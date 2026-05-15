package com.example.softwareengineerticketproject;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

    @FXML
    private Label errorLabel;

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

    // this method allows the create ticket button to work. it creates ticket and wipes info from boxes
    @FXML
    private void submitTicketButtonClicked() throws IOException{
        if(createTicket()){
            subjectTextField.setText("");
            descriptionTextArea.setText("");
            deviceChoiceBox.setValue("Device");
            issueChoiceBox.setValue("Issue");
            errorLabel.setText("");
        }
    }

    /*
    this method perform x actions
        - checks all fields are filled out
        - creates a ticket in firestore
     */
    private boolean createTicket() throws IOException{

        // checks all fields
        if(subjectTextField.getText().isEmpty() ||
                descriptionTextArea.getText().isEmpty() ||
                deviceChoiceBox.getValue().contentEquals("Device") ||
                issueChoiceBox.getValue().contentEquals("Issue")){

            errorLabel.setText("all fields must be properly filled out");
            return false;

        }

        // create ticket
        Map<String, Object> userMap = new HashMap<>();

        // gets ID
        DocumentReference docRef =
                TicketManagerApplication.fstore.collection("Tickets").document();

        String ID = docRef.getId();   // <-- Firestore-generated ID

        userMap.put("ID", ID);
        userMap.put("subject", subjectTextField.getText());
        userMap.put("deviceInfo", deviceChoiceBox.getValue());
        userMap.put("issueType", issueChoiceBox.getValue());
        userMap.put("completion", false);
        userMap.put("descriptionIssue", descriptionTextArea.getText());
        userMap.put("userID", sessionUsername);

        ApiFuture<WriteResult> result = docRef.set(userMap);

        return true;


    }

}
