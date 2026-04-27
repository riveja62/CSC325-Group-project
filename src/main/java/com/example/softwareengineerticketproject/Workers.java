package com.example.softwareengineerticketproject;

public class Workers extends Persons{
    public Workers(int ID, String FirstName, String LastName, String Username, String Password, String Email){
        super(ID, FirstName, LastName, Username, Password, Email);
    }

    //aisyhfdg

    public void displayInfo(){
        System.out.println("Employee ID: " + getID());
        System.out.println("Employee Name: " + getFirstName() + " " + getLastName());
        System.out.println("Employee Username: " + getUsername());
        System.out.println("Employee Password: " + getPassword());
        System.out.println("Employee Email: " + getEmail());
    }
}