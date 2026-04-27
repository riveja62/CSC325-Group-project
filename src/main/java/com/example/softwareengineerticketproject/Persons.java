package com.example.softwareengineerticketproject;

public abstract class Persons {
    protected int ID;
    protected String FirstName;
    protected String LastName;
    protected String Password;
    String Email;
    String Username;

    public Persons(int ID, String FirstName, String LastName, String Password, String Email, String Username){
        this.ID = ID;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Password = Password;
        this.Email = Email;
        this.Username = Username;
    }

    // this is all the setters

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setEmail(String email) {
        Email = email;
    }

    // this is all the getters

    public int getID() {
        return ID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getPassword() {
        return Password;
    }

    public String getEmail() {
        return Email;
    }

    public String getUsername() {
        return Username;
    }

    public abstract void displayInfo();
}
