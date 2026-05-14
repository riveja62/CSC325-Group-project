package com.example.softwareengineerticketproject;

/*
This class is an object class for the worker user. It provides a constructor to create a worker and extends the person
class. It contains these methods:
    * displayInfo - allows us to display a worker's info if needed

*/

public class Workers extends Persons{

    // this is the constructor
    public Workers(String ID, String FirstName, String LastName, String Username, String Password, String Email){
        super(ID, FirstName, LastName, Username, Password, Email);
    }

    // this method is used to display a workers info, if needed, in the output pane
    public void displayInfo(){
        System.out.println("Employee ID: " + getID());
        System.out.println("Employee Name: " + getFirstName() + " " + getLastName());
        System.out.println("Employee username: " + getUsername());
        System.out.println("Employee password: " + getPassword());
        System.out.println("Employee email: " + getEmail());
    }
}