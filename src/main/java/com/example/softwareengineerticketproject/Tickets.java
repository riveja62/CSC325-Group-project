package com.example.softwareengineerticketproject;

/*
This class is an object class for the tickets object. It provides a constructor to create a ticket. It contains these
methods:
    * setters and getters - allows the ability to manipulate and get an object's information, this is useful for
                            firebase integration

*/


public class Tickets{
    int ID;
    String DeviceInfo;
    String IssueType;
    Boolean Completion;
    String DescriptionIssue;
    int UserID;

    // this is the constructor
    public Tickets(int ID, String DeviceInfo, String IssueType,Boolean Completion, String DescriptionIssue, int UserID){
        this.ID = ID;
        this.DeviceInfo = DeviceInfo;
        this.IssueType = IssueType;
        this.Completion = Completion;
        this.DescriptionIssue = DescriptionIssue;
        this.UserID = UserID;
    }

    // this is the getters

    public int getID() {
        return ID;
    }

    public String getDeviceInfo() {
        return DeviceInfo;
    }

    public String getIssueType() {
        return IssueType;
    }

    public Boolean getCompletion() {
        return Completion;
    }

    public String getDescriptionIssue() {
        return DescriptionIssue;
    }

    public int getUserID() {
        return UserID;
    }

    // this is the setters

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setDeviceInfo(String deviceInfo) {
        DeviceInfo = deviceInfo;
    }

    public void setIssueType(String issueType) {
        IssueType = issueType;
    }

    public void setCompletion(Boolean completion) {
        Completion = completion;
    }

    public void setDescriptionIssue(String descriptionIssue) {
        DescriptionIssue = descriptionIssue;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }
}
