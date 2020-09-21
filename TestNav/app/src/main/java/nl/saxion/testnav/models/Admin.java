package nl.saxion.testnav.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Admin {
    private static List<Customer> customers;
    private static List<Courier> couriers;
    private static List<Order> orders;
    private static List<Restaurant> restaurants;

    //sample info
    private double total = ThreadLocalRandom.current().nextDouble(10, 100);
    private String items = "";

    static {
        customers = new ArrayList<>();
        couriers = new ArrayList<>();
        orders = new ArrayList<>();
        restaurants = new ArrayList<>();
    }

    public boolean addRestaurant(Restaurant r) {
        if (restaurants.contains(r)) return false;
        else restaurants.add(r);
        return true;
    }

    public boolean addAccount(Account a) {
        if (a instanceof Customer) {
            for (Customer c : customers) {
                if (c.getEmail().equals(a.getEmail())) {
                    return false;
                } else {
                    customers.add((Customer) a);
                    return true;
                }
            }
        } else if (a instanceof Courier) {
            for (Courier d : couriers) {
                if (d.getEmail().equals(a.getEmail())) {
                    return false;
                } else {
                    couriers.add((Courier) a);
                    return true;
                }
            }
        }
        return false;
    }

    //TODO: clean up the item and total later
    public boolean createOrder(Customer c, Date chosenTime, Restaurant r){
        if(c.getCurrentOrder() == null){
            Order o = new Order(chosenTime, c.getFirstName())

        }
        return false;
    }

    public boolean createNoneAccountOrder(String first_name, String last_name,
                                          String phone_no, String streetAddress, String zipcode,
                                          String city, Date chosenTime, Restaurant r){
        orders.add(new Order(chosenTime, first_name, last_name, phone_no, streetAddress, zipcode,
                city, r, total, items));
        return true
    }
}
