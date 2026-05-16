package com.example.softwareengineerticketproject;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/*
This class is the controller for the client home page, it provides the ability for it to function. It contains these
methods:
    * Initialize - sets up user session and page elements
    * setUsername - assigns user to create session
    * logoutButtonClicked - allows logout button to work
    * submitTicketButtonClicked - allows submit ticket button to work
    * createTicket - this creates a new ticket in firestore
    * setupCreateTableColumns - this sets up the create tab table for displaying tickets
    * loadAllClientTickets - this is a query for the clients tickets

 */

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

        //sets up create tab table
        setupCreateTableColumns();

        // sets up past tab table
        setupPastTableColumns();

        // loads up tickets
        loadAllClientTickets();

        // activates listener
        setupRowSelection();
    }

    // create tab table elements
    @FXML private TableView<Tickets> createTable;
    @FXML private TableColumn<Tickets, String> createSubjectColumn;
    @FXML private TableColumn<Tickets, Boolean> createCompletedColumn;

    // past tab table elements
    @FXML private TableView<Tickets> pastTable;
    @FXML private TableColumn<Tickets, String> pastSubjectColumn;
    @FXML private TableColumn<Tickets, String> pastDeviceColumn;
    @FXML private TableColumn<Tickets, String> pastIssueColumn;
    @FXML private TableColumn<Tickets, Boolean> pastCompletedColumn;

    // past tab side panel elements
    @FXML private Label subjectLabel;
    @FXML private Label completedLabel;
    @FXML private Label deviceLabel;
    @FXML private Label issueLabel;
    @FXML private TextArea pastDescriptionTextArea;

    private Tickets selectedTicket;

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
    this method perform 2 actions
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

    // this method sets up each of the columns in the create tab table to be able to display the values
    private void setupCreateTableColumns() {
        createSubjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        createCompletedColumn.setCellValueFactory(new PropertyValueFactory<>("completion"));
    }

    // this method sets up each of the columns in the past tab table to be able to display the values
    private void setupPastTableColumns(){
        pastSubjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        pastDeviceColumn.setCellValueFactory(new PropertyValueFactory<>("deviceInfo"));
        pastIssueColumn.setCellValueFactory(new PropertyValueFactory<>("issueType"));
        pastCompletedColumn.setCellValueFactory(new PropertyValueFactory<>("completion"));
    }

    // this is a listener that detects which row is selected on the past table
    private void setupRowSelection() {
        pastTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldTicket, newTicket) -> {
                    if (newTicket != null) {
                        selectedTicket = newTicket;
                        showTicketDetails(newTicket);
                    }
                }
        );
    }

    // this shows the details of the selected ticket on the past table view on the left panel
    private void showTicketDetails(Tickets ticket) {
        subjectLabel.setText(ticket.getSubject());
        deviceLabel.setText(ticket.getDeviceInfo());
        issueLabel.setText(ticket.getIssueType());
        pastDescriptionTextArea.setText(ticket.getDescriptionIssue());
        completedLabel.setText(String.valueOf(ticket.getCompletion()));
    }



    // this method loads all the clients tickets
    private void loadAllClientTickets() {
        try {
            ApiFuture<QuerySnapshot> future =
                    TicketManagerApplication.fstore.collection("Tickets")
                            .whereEqualTo("userID", sessionUsername)
                            .get();

            List<QueryDocumentSnapshot> docs = future.get().getDocuments();
            ObservableList<Tickets> ticketList = FXCollections.observableArrayList();

            for (QueryDocumentSnapshot doc : docs) {
                Tickets t = new Tickets(
                        doc.getString("ID"),
                        doc.getString("subject"),
                        doc.getString("deviceInfo"),
                        doc.getString("issueType"),
                        doc.getBoolean("completion"),
                        doc.getString("descriptionIssue"),
                        doc.getString("userID")
                );
                ticketList.add(t);
            }

            createTable.setItems(ticketList);
            pastTable.setItems(ticketList);

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }


}
