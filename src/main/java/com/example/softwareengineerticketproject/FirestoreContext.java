package com.example.softwareengineerticketproject;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;

/*
this class utilizes the fire store's provided code to access and utilize the fire store database and authentication
features
*/

public class FirestoreContext {

    public Firestore firebase() {
        try {

            FileInputStream serviceAccount =
                    new FileInputStream("src/main/resources/com/example/softwareengineerticketproject/key.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);

        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return FirestoreClient.getFirestore();
    }


}
