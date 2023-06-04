package com.nure.postalOffice.AbstractDBObjects;

public abstract class AbstractPackage {
    private long id;
    private String sender;
    private String receiver;

    private int senderDepartmentId;
    private String senderDepartmentCity;
    private String senderDepartmentCountry;
    private String senderDepartmentNumber;

    private int receiverDepartmentId;
    private String receiverDepartmentCity;
    private String receiverDepartmentCountry;
    private String receiverDepartmentNumber;

    public AbstractPackage(long id, String sender, String receiver, int senderDepartmentId, String senderDepartmentCity, String senderDepartmentCountry, String senderDepartmentNumber, int receiverDepartmentId, String receiverDepartmentCity, String receiverDepartmentCountry, String receiverDepartmentNumber) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.senderDepartmentId = senderDepartmentId;
        this.senderDepartmentCity = senderDepartmentCity;
        this.senderDepartmentCountry = senderDepartmentCountry;
        this.senderDepartmentNumber = senderDepartmentNumber;
        this.receiverDepartmentId = receiverDepartmentId;
        this.receiverDepartmentCity = receiverDepartmentCity;
        this.receiverDepartmentCountry = receiverDepartmentCountry;
        this.receiverDepartmentNumber = receiverDepartmentNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public int getSenderDepartmentId() {
        return senderDepartmentId;
    }

    public void setSenderDepartmentId(int senderDepartmentId) {
        this.senderDepartmentId = senderDepartmentId;
    }

    public String getSenderDepartmentCity() {
        return senderDepartmentCity;
    }

    public void setSenderDepartmentCity(String senderDepartmentCity) {
        this.senderDepartmentCity = senderDepartmentCity;
    }

    public String getSenderDepartmentCountry() {
        return senderDepartmentCountry;
    }

    public void setSenderDepartmentCountry(String senderDepartmentCountry) {
        this.senderDepartmentCountry = senderDepartmentCountry;
    }

    public String getSenderDepartmentNumber() {
        return senderDepartmentNumber;
    }

    public void setSenderDepartmentNumber(String senderDepartmentNumber) {
        this.senderDepartmentNumber = senderDepartmentNumber;
    }

    public int getReceiverDepartmentId() {
        return receiverDepartmentId;
    }

    public void setReceiverDepartmentId(int receiverDepartmentId) {
        this.receiverDepartmentId = receiverDepartmentId;
    }

    public String getReceiverDepartmentCity() {
        return receiverDepartmentCity;
    }

    public void setReceiverDepartmentCity(String receiverDepartmentCity) {
        this.receiverDepartmentCity = receiverDepartmentCity;
    }

    public String getReceiverDepartmentCountry() {
        return receiverDepartmentCountry;
    }

    public void setReceiverDepartmentCountry(String receiverDepartmentCountry) {
        this.receiverDepartmentCountry = receiverDepartmentCountry;
    }

    public String getReceiverDepartmentNumber() {
        return receiverDepartmentNumber;
    }

    public void setReceiverDepartmentNumber(String receiverDepartmentNumber) {
        this.receiverDepartmentNumber = receiverDepartmentNumber;
    }

}
