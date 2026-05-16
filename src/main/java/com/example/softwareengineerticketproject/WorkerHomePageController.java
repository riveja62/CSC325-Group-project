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
this class is a controller for the worker home page. it contains the following methods:
    * initialize - this sets user session and page elements
    * setUsername - this sets the username for the user session
    * logoutButtonClicked - this lets the logout button work
    * setupTableColumns - this sets up the table for displaying tickets
    * setupRowSelection - this is a listener for the table
    * showTicketDetails - this displays a tickets info to the side panel
    * completionToggled - this lets the completion checkbox work
    * updateTicketCompletion - this updates completion for a ticket in firestore
    * loadAllTickets - this is a query for tickets

 */

public class WorkerHomePageController {

    // this method sets user session as well as populating the table
    @FXML
    public void initialize() {

        // assigns the label
        usernameLabel.setText(sessionUsername);

        // populates device choice box
        deviceChoiceBox.getItems().addAll("Phone", "Tablet", "Computer", "Other");

        // populates issue choice box
        issueChoiceBox.getItems().addAll("Lags", "Freezes", "Crashes", "Other");

        // sets default choices
        deviceChoiceBox.setValue("Device");
        issueChoiceBox.setValue("Issue");

        // sets up tickets tab table
        setupTicketsTableColumns();

        // loads all the tickets in the database
        loadAllTickets();

        // activates listener
        setupRowSelection();
    }

    // labels
    @FXML private Label usernameLabel;

    // Buttons
    @FXML private Button logoutButton;

    // tickets tab table elements
    @FXML private TableView<Tickets> ticketsTableView;
    @FXML private TableColumn<Tickets, String> subjectColumn;
    @FXML private TableColumn<Tickets, String> issueColumn;
    @FXML private TableColumn<Tickets, String> deviceColumn;
    @FXML private TableColumn<Tickets, Boolean> completionColumn;
    @FXML private TableColumn<Tickets, String> userColumn;

    // tickets tab side panel elements
    @FXML private Label subjectLabel;
    @FXML private Label deviceLabel;
    @FXML private Label issueLabel;
    @FXML private Label userLabel;
    @FXML private TextArea descriptionTextArea;
    @FXML private CheckBox completionCheckBox;

    // updates side panel table elements
    @FXML private TableView<Updates> updatesTableView;
    @FXML private TableColumn<Updates, String> updatesSubjectColumn;

    // updates creation elements
    @FXML private TextField updatesSubjectTextField;
    @FXML private ChoiceBox<String> deviceChoiceBox;
    @FXML private ChoiceBox<String> issueChoiceBox;
    @FXML private TextArea updatesDescriptionTextArea;
    @FXML private Button submitUpdateButton;
    @FXML private Label errorLabel;


    private Tickets selectedTicket;

    // session user variable
    private static String sessionUsername;

    // this method sets the session username
    public static void setUsername(String username) {
        sessionUsername = username;
    }

    /*
    this method is activated when the logout button is clicked, it clears the session and redirects to worker login
    page.
     */
    @FXML
    private void logoutButtonClicked() throws IOException {
        sessionUsername = null;
        TicketManagerApplication.setRoot("WorkerLoginPage");
    }

    // this method is activated when the submit update button is clicked and submits an update and clears the fields
    @FXML
    private void submitUpdateButtonClicked() throws IOException{
        if(createUpdate()){
            updatesSubjectTextField.setText("");
            updatesDescriptionTextArea.setText("");
            deviceChoiceBox.setValue("Device");
            issueChoiceBox.setValue("Issue");
            errorLabel.setText("");
            updatesTableView.refresh();
        }
    }


    // this method sets up each of the columns in the table to be able to display the values
    private void setupTicketsTableColumns() {
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        issueColumn.setCellValueFactory(new PropertyValueFactory<>("issueType"));
        deviceColumn.setCellValueFactory(new PropertyValueFactory<>("deviceInfo"));
        completionColumn.setCellValueFactory(new PropertyValueFactory<>("completion"));
        userColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
    }

    // this is a listener that detects which row is selected on the table
    private void setupRowSelection() {
        ticketsTableView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldTicket, newTicket) -> {
                    if (newTicket != null) {
                        selectedTicket = newTicket;
                        showTicketDetails(newTicket);
                    }
                }
        );
    }

    // this shows the details of the selected ticket on the table view on the left panel
    private void showTicketDetails(Tickets ticket) {
        subjectLabel.setText(ticket.getSubject());
        deviceLabel.setText(ticket.getDeviceInfo());
        issueLabel.setText(ticket.getIssueType());
        userLabel.setText(ticket.getUserID());
        descriptionTextArea.setText(ticket.getDescriptionIssue());
        completionCheckBox.setSelected(Boolean.TRUE.equals(ticket.getCompletion()));
    }

    // this method is activated by the completed checkbox and calls updateTicketCompletion
    @FXML
    private void completionToggled() {
        if (selectedTicket == null) return;

        boolean newStatus = completionCheckBox.isSelected();
        updateTicketCompletion(selectedTicket, newStatus);
    }

    // this method updates the completion status in firestore
    private void updateTicketCompletion(Tickets ticket, boolean newStatus) {
        TicketManagerApplication.fstore.collection("Tickets")
                .document(ticket.getID())
                .update("completion", newStatus);

        ticket.setCompletion(newStatus);
        ticketsTableView.refresh();
    }

    // this method gets a query of all the tickets and then puts them into the table view
    private void loadAllTickets() {
        try {
            ApiFuture<QuerySnapshot> future =
                    TicketManagerApplication.fstore.collection("Tickets").get();

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

            ticketsTableView.setItems(ticketList);

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

        /*
    this method perform 2 actions
        - checks all fields are filled out
        - creates an update in firestore
     */
    private boolean createUpdate() throws IOException{

        // checks all fields
        if(updatesSubjectTextField.getText().isEmpty() ||
                updatesDescriptionTextArea.getText().isEmpty() ||
                deviceChoiceBox.getValue().contentEquals("Device") ||
                issueChoiceBox.getValue().contentEquals("Issue")){

            errorLabel.setText("all fields must be properly filled out");
            return false;

        }

        // create ticket
        Map<String, Object> userMap = new HashMap<>();

        // gets ID
        DocumentReference docRef =
                TicketManagerApplication.fstore.collection("Updates").document();

        String ID = docRef.getId();   // <-- Firestore-generated ID

        userMap.put("ID", ID);
        userMap.put("subject", updatesSubjectTextField.getText());
        userMap.put("deviceInfo", deviceChoiceBox.getValue());
        userMap.put("issueType", issueChoiceBox.getValue());
        userMap.put("description", updatesDescriptionTextArea.getText());

        ApiFuture<WriteResult> result = docRef.set(userMap);

        return true;

    }
}
