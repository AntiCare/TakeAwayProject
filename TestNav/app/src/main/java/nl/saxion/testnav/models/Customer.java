package nl.saxion.testnav.models;

import java.util.Date;

public class Customer extends Account{
    //fields
    private String streetAddress, zipcode, city;
    private Order currentOrder;

    public Customer(String email, String password, String first_name, String last_name, String phone_no, String streetAddress, String zipcode, String city) {
        super(email, password, first_name, last_name, phone_no);
        this.streetAddress = streetAddress;
        this.zipcode = zipcode;
        this.city = city;
    }


    //#region getters setters

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }
    //#endregion


}
