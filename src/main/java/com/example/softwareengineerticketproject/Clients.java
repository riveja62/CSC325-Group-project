package com.example.softwareengineerticketproject;

/*
This class is an object class for the client user. It provides a constructor to create a client and extends the person
class. It contains these methods:
    * displayInfo - allows us to display a client's info if needed

*/

public class Clients extends Persons{

    // this is the constructor
    public Clients(int ID, String FirstName, String LastName, String Username, String Password, String Email){
        super(ID, FirstName, LastName, Username, Password, Email);
    }

    // this method is used to display a clients info, if needed, in the output pane
    public void displayInfo(){
        System.out.println("Clients ID: " + getID());
        System.out.println("Clients Name: " + getFirstName() + " " + getLastName());
        System.out.println("Clients Username: " + getUsername());
        System.out.println("Clients Password: " + getPassword());
        System.out.println("Clients Email: " + getEmail());
    }
}
