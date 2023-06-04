package com.nure.postalOffice.DBObjects;

import com.nure.postalOffice.AbstractDBObjects.AbstractPackage;

import java.util.Date;

public class CreatedPack extends AbstractPackage {
    private Date dateCreating;

    public CreatedPack(long id, String sender, String receiver, int senderDepartmentId, String senderDepartmentCity, String senderDepartmentCountry, String senderDepartmentNumber, int receiverDepartmentId, String receiverDepartmentCity, String receiverDepartmentCountry, String receiverDepartmentNumber, Date dateCreating) {
        super(id, sender, receiver, senderDepartmentId, senderDepartmentCity, senderDepartmentCountry, senderDepartmentNumber, receiverDepartmentId, receiverDepartmentCity, receiverDepartmentCountry, receiverDepartmentNumber);
        this.dateCreating = dateCreating;
    }

    public Date getDateCreating() {
        return dateCreating;
    }

    public void setDateCreating(Date dateCreating) {
        this.dateCreating = dateCreating;
    }

}
