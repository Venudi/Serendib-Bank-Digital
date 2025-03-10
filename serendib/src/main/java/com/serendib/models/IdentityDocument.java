package com.serendib.models;

public class IdentityDocument {
    private IdType idType;
    private String idNumber;

    public IdentityDocument() {}
    
    public IdentityDocument(IdType idType, String idNumber) {
        this.idType = idType;
        this.idNumber = idNumber;
    }

    public IdType getIdType() { return idType; }
    public String getIdNumber() { return idNumber; }
}
