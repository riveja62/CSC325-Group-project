package com.example.softwareengineerticketproject;

public class Workers extends Persons{
    public Workers(int ID, String FirstName, String LastName, String Username, String Password, String Email){
        super(ID, FirstName, LastName, Username, Password, Email);
    }

    public void displayInfo(){
        System.out.println("Employee ID: " + ID());
        System.out.println("Employee Name: " + FirstName() + " " + LastName());
        System.out.println("Employee Username: " + Username());
        System.out.println("Employee Password: " + Password());
        System.out.println("Employee Email: " + Email());
    }
}