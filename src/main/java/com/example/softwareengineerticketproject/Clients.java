package com.example.softwareengineerticketproject;

public class Clients extends Persons{
    public Clients(int ID, String FirstName, String LastName, String Username, String Password, String Email){
        super(ID, FirstName, LastName, Username, Password, Email);
    }

    public void displayInfo(){
        System.out.println("Clients ID: " + ID());
        System.out.println("Clients Name: " + FirstName() + " " + LastName());
        System.out.println("Clients Username: " + Username());
        System.out.println("Clients Password: " + Password());
        System.out.println("Clients Email: " + Email());
    }
}
