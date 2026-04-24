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


    public int ID(){
        return ID;
    }

    public String FirstName(){
        return FirstName;
    }

    public String LastName(){
        return LastName;
    }

    public String Password(){
        return Password;
    }

    public String Email(){
        return Email;
    }

    public String Username(){
        return Username;
    }


    public void setID(int ID){
        this.ID = ID;
    }

    public void setFirstName(String FirstName){
        this.FirstName = FirstName;
    }

    public void setLastName(String LastName){
        this.LastName = LastName;
    }

    public void setPassword(String Password){
        this.Password = Password;
    }

    public void setEmail(String Email){
        this.Password = Email;
    }

    public void setUsername(String Username){
        this.Password = Username;
    }

    public abstract void displayInfo();
}
