package com.example.softwareengineerticketproject;

/*
This class is an object class for the tickets object. It provides a constructor to create a ticket. It contains these
methods:
    * setters and getters - allows the ability to manipulate and get an object's information, this is useful for
                            firebase integration

*/


public class Tickets{
    String ID;
    String subject;
    String deviceInfo;
    String issueType;
    Boolean completion;
    String descriptionIssue;
    String userID;

    // this is the constructor
    public Tickets(String ID, String Subject,String DeviceInfo, String IssueType,Boolean Completion, String DescriptionIssue, String UserID){
        this.ID = ID;
        this.subject = Subject;
        this.deviceInfo = DeviceInfo;
        this.issueType = IssueType;
        this.completion = Completion;
        this.descriptionIssue = DescriptionIssue;
        this.userID = UserID;
    }

    // this is the getters

    public String getID() {
        return ID;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public String getIssueType() {
        return issueType;
    }

    public Boolean getCompletion() {
        return completion;
    }

    public String getDescriptionIssue() {
        return descriptionIssue;
    }

    public String getUserID() {
        return userID;
    }

    // this is the setters

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public void setCompletion(Boolean completion) {
        this.completion = completion;
    }

    public void setDescriptionIssue(String descriptionIssue) {
        this.descriptionIssue = descriptionIssue;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
