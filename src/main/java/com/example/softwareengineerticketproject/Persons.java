package com.example.softwareengineerticketproject;

public abstract class Persons {
    protected int ID;
    protected String FirstName;
    protected String LastName;
    protected String Password;

    public Persons(int ID, String FirstName, String LastName, String Password){
        this.ID = ID;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Password = Password;
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
}
