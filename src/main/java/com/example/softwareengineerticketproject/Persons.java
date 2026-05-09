package com.example.softwareengineerticketproject;

/*
This class is the parent class for the Worker and Client classes. It sets up everything for them and contains these
methods:
    * getters and setters - allows the ability to manipulate and get an object's information, this is useful for
                            firebase integration
    * abstract display info - this forces the children classes to make their version of display info

*/

public abstract class Persons {
    protected int ID;
    protected String firstName;
    protected String lastName;
    protected String password;
    protected String email;
    protected String username;

    // this is the person constructor
    public Persons(int ID, String FirstName, String LastName, String Password, String Email, String Username){
        this.ID = ID;
        this.firstName = FirstName;
        this.lastName = LastName;
        this.password = Password;
        this.email = Email;
        this.username = Username;
    }

    // this is all the setters

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // this is all the getters

    public int getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    // this forces the children classes to make their version of display info
    public abstract void displayInfo();
}
