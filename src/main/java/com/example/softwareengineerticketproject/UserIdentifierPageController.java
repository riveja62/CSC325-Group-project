package com.example.softwareengineerticketproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

/*
This class is the controller for the user identifier page, it provides the ability for it to function. It contains these
methods:
    * clientButtonClicked - allows client button to function
    * workerButtonClicked - allows worker button to function

*/

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
