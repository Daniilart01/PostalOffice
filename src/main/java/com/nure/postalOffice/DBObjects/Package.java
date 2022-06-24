package com.nure.postalOffice.DBObjects;

import com.nure.postalOffice.AbstractDBObjects.AbstractPackage;

import java.util.Date;

public class Package extends AbstractPackage {

    private String status;
    private Date dateDeparture;
    private Date dateReceiving;


    public Package(long id, String sender, String receiver, int senderDepartmentId, String senderDepartmentCity, String senderDepartmentCountry, String senderDepartmentNumber, int receiverDepartmentId, String receiverDepartmentCity, String receiverDepartmentCountry, String receiverDepartmentNumber, String status, Date dateDeparture, Date dateReceiving) {
        super(id, sender, receiver, senderDepartmentId, senderDepartmentCity, senderDepartmentCountry, senderDepartmentNumber, receiverDepartmentId, receiverDepartmentCity, receiverDepartmentCountry, receiverDepartmentNumber);
        this.status = status;
        this.dateDeparture = dateDeparture;
        this.dateReceiving = dateReceiving;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateDeparture() {
        return dateDeparture;
    }

    public void setDateDeparture(Date dateDeparture) {
        this.dateDeparture = dateDeparture;
    }

    public Date getDateReceiving() {
        return dateReceiving;
    }

    public void setDateReceiving(Date dateReceiving) {
        this.dateReceiving = dateReceiving;
    }

}
