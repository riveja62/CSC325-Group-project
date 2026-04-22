package com.example.softwareengineerticketproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class UserIdentifierPageController {
    @FXML
    private Button clientButton;

    @FXML
    private Button workerButton;

    @FXML
    private void clientButtonClicked() throws IOException{
        TicketManagerApplication.setRoot("ClientLoginPage");
    }

    @FXML
    private void workerButtonClicked() throws IOException{
        TicketManagerApplication.setRoot("WorkerLoginPage");
    }

}
