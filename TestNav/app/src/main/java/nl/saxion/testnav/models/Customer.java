package nl.saxion.testnav.models;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Account {
    //fields
    private String streetAddress, zipcode, city;
    private List<OrderItem> currentOrders;
    private List<Restaurant> favorites;

    public Customer(String email, String password, String first_name, String last_name, String phone_no, String streetAddress, String zipcode, String city) {
        super(email, password, first_name, last_name, phone_no);
        this.streetAddress = streetAddress;
        this.zipcode = zipcode;
        this.city = city;
        this.currentOrders = new ArrayList<>();
        this.favorites = new ArrayList<>();
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

    public List<OrderItem> getCurrentOrders() {
        return currentOrders;
    }

    public List<Restaurant> getAllFavorites() { return favorites;}

    //#endregion

    public void addOrder(OrderItem order) {
        this.currentOrders.add(order);
    }

    public boolean addToFavorites(Restaurant r) {
        if (this.favorites.contains(r)) {
            return false;
        } else {
            this.favorites.add(r);
            return true;
        }
    }
}
