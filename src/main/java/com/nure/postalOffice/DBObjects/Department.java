package com.nure.postalOffice.DBObjects;

public class Department {
    private int id;
    private String city;
    private String country;
    private String number;

    public Department(int id, String city, String country, String number) {
        this.id = id;
        this.city = city;
        this.country = country;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}