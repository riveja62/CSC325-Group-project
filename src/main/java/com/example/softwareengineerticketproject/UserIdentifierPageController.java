package com.example.softwareengineerticketproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class UserIdentifierPageController {

    // buttons
    @FXML
    private Button clientButton;

    @FXML
    private Button workerButton;

    // this method is activated by clicking the client button and redirects to client login
    @FXML
    private void clientButtonClicked() throws IOException{
        TicketManagerApplication.setRoot("ClientLoginPage");
    }

    // this method is activated by clicking the worker button and redirects to worker login
    @FXML
    private void workerButtonClicked() throws IOException{
        TicketManagerApplication.setRoot("WorkerLoginPage");
    }

}
