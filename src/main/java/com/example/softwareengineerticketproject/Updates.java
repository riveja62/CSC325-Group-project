package com.example.softwareengineerticketproject;

/*
This class is an object class for the updates object. It provides a constructor to create an update. It contains these
methods:
    * setters and getters - allows the ability to manipulate and get an object's information, this is useful for
                            firebase integration

*/

public class Updates {
    String ID;
    String subject;
    String deviceInfo;
    String issueType;
    String description;

    // this is a constructor
    public Updates (String ID, String subject, String deviceInfo, String issueType, String description){
        this.ID = ID;
        this.subject = subject;
        this.deviceInfo = deviceInfo;
        this.issueType = issueType;
        this.description = description;
    }

    // this is getters

    public String getID() {
        return ID;
    }

    public String getSubject() {
        return subject;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public String getIssueType() {
        return issueType;
    }

    public String getDescription() {
        return description;
    }

    // this is setters


    public void setID(String ID) {
        this.ID = ID;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
