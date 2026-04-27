package com.example.softwareengineerticketproject;

public class Clients extends Persons{
    public Clients(int ID, String FirstName, String LastName, String Username, String Password, String Email){
        super(ID, FirstName, LastName, Username, Password, Email);
    }

    public void displayInfo(){
        System.out.println("Clients ID: " + getID());
        System.out.println("Clients Name: " + getFirstName() + " " + getLastName());
        System.out.println("Clients Username: " + getUsername());
        System.out.println("Clients Password: " + getPassword());
        System.out.println("Clients Email: " + getEmail());
    }
}
