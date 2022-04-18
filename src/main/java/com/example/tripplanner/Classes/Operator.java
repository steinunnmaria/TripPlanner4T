package com.example.tripplanner.Classes;

public class Operator {
    private String operatorId;
    private String name;
    private String phoneNo;
    private String location;
    private int localCode;

    public Operator(String operatorId, String name, String phoneNo, String location, int localCode) {
        this.operatorId = operatorId;
        this.name = name;
        this.phoneNo = phoneNo;
        this.location = location;
        this.localCode = localCode;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public String getName() {
        return this.name;
    }

    public String getLocation() {
        return this.location;
    }

    public String getPhoneNo() {
        return this.phoneNo;
    }

    public int getLocalCode() {
        return this.localCode;
    }
}
