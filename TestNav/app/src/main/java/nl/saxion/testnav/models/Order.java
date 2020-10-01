package nl.saxion.testnav.models;

import androidx.annotation.NonNull;

import java.util.Calendar;
import java.util.Date;

public class Order {
    //fields
    private static int id = 0;
    private double total;
    private Date timeCreated, actualDeliver, chosenDeliver;
    private String conversation, items, fName, lName, phoneNo, streetAdd, zipcode, city;
    private Courier deliverer;
    private Restaurant restaurant;
    private ORDER_STATUS status;


    public Order (Date chosenDeliver, String first_name, String last_name, String phone_no, String streetAddress, String zipcode,
                  String city, Restaurant restaurant, double total, String items){
        id++;
        this.timeCreated = Calendar.getInstance().getTime();
        this.chosenDeliver = chosenDeliver;
        this.restaurant = restaurant;
        this.total = total;
        this.fName = first_name;
        this.lName = last_name;
        this.phoneNo = phone_no;
        this.streetAdd = streetAddress;
        this.zipcode = zipcode;
        this.city = city;
        this.status = ORDER_STATUS.SEARCHING;
    }

    //#region getters setters
    public Date getTimeCreated() {
        return timeCreated;
    }

    public Date getChosenDeliver() {
        return chosenDeliver;
    }

    public String getFirstName() {
        return fName;
    }

    public String getLastName() {
        return lName;
    }

    public String getStreetAdd() {
        return streetAdd;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getConversation() {
        return conversation;
    }

    public Courier getDeliverer() {
        return deliverer;
    }

    public void setDeliverer(Courier deliverer) {
        this.deliverer = deliverer;
        setStatus(ORDER_STATUS.ON_GOING);
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public double getTotal() {
        return total;
    }

    public void setStatus(ORDER_STATUS status) {
        this.status = status;
    }
    //#endregion
    @NonNull
    @Override
    public String toString() {//TODO: reformat DATETIME
        return id + " - " + this.timeCreated + "\nCustomer name: " + this.fName
                + " \nDeliverer:"  + this.deliverer.getFirstName() + "\nStatus: " + this.status
                + "\nRestaurants: " + this.restaurant.getName() + "\nTotal: " + this.total
                + "\nChosen delivery time: " + this.chosenDeliver + "\nActually deliver time: "
                + this.actualDeliver;
    }

    public void addMessage(String message) {
        conversation += "\n" + message;
    }
}

