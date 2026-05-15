package com.example.softwareengineerticketproject;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class WorkerHomePageController {

    // assigns the username to label when this page starts
    @FXML
    public void initialize(){
        usernameLabel.setText(sessionUsername);

        idColumn.setCellValueFactory(
                new PropertyValueFactory<>("ID"));

        subjectColumn.setCellValueFactory(
                new PropertyValueFactory<>("subject"));

        deviceInfoColumn.setCellValueFactory(
                new PropertyValueFactory<>("deviceInfo"));

        issueTypeColumn.setCellValueFactory(
                new PropertyValueFactory<>("issueType"));

        completionColumn.setCellValueFactory(
                new PropertyValueFactory<>("completion"));

        descriptionIssueColumn.setCellValueFactory(
                new PropertyValueFactory<>("descriptionIssue"));

        userIDColumn.setCellValueFactory(
                new PropertyValueFactory<>("userID"));

        loadTableData();
    }

    private void loadTableData() {

        ObservableList<Tickets> ticketList =
                FXCollections.observableArrayList();

        Firestore db = FirestoreContext.getDB();

        ApiFuture<QuerySnapshot> future =
                db.collection("Tickets").get();

        try {

            List<QueryDocumentSnapshot> documents =
                    future.get().getDocuments();

            for (QueryDocumentSnapshot document : documents) {

                String id = document.getString("ID");
                String subject = document.getString("subject");
                String DeviceInfo = document.getString("deviceInfo");
                String issueType = document.getString("issueType");
                Boolean completion = document.getBoolean("completion");
                String descriptionIssue = document.getString("descriptionIssue");
                String userID = document.getString("userID");

                Tickets ticket = new Tickets(id, subject,DeviceInfo, issueType, completion, descriptionIssue, userID);

                ticketList.add(ticket);
            }

            ticketTable.setItems(ticketList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // labels
    @FXML
    private Label usernameLabel;

    // Buttons
    @FXML
    private Button logoutButton;

    // Tableview parts
    @FXML
    private TableView<Tickets> ticketTable;

    @FXML
    private TableColumn<Tickets, String> idColumn;

    @FXML
    private TableColumn<Tickets, String> subjectColumn;

    @FXML
    private TableColumn<Tickets, String> deviceInfoColumn;

    @FXML
    private TableColumn<Tickets, String> issueTypeColumn;

    @FXML
    private TableColumn<Tickets, Boolean> completionColumn;

    @FXML
    private TableColumn<Tickets, String> descriptionIssueColumn;

    @FXML
    private TableColumn<Tickets, String> userIDColumn;


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
