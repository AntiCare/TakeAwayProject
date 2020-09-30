package nl.saxion.testnav.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Admin {
    private static List<Customer> customers;
    private static List<Courier> couriers;
    private static List<Order> orders;
    private static List<Restaurant> restaurants;

    //TODO: GET RID OF SAMPLE INFO AND
    //sample info
    private double total = ThreadLocalRandom.current().nextDouble(10, 100);
    private String items = "";

    static {
        customers = new ArrayList<>();
        couriers = new ArrayList<>();
        orders = new ArrayList<>();
        restaurants = new ArrayList<>();
    }

    public List<Order> getCourierOrders(Courier courier){
        return courier.getAllOrders();
    }
    public List<Order> getCustomerCurrentOrders(Customer customer){
        return customer.getCurrentOrders();
    }

    public static boolean addRestaurant(Restaurant r) {
        if (restaurants.contains(r)) return false;
        else restaurants.add(r);
        return true;
    }

    public static List<Restaurant> getRestaurants() {
        return restaurants;
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
    public boolean createOrder(Customer c, Date chosenTime, Restaurant r) {
        if (c.getCurrentOrders().size() < 5) {
            Order o = new Order(chosenTime, c.getFirstName(), c.getLastName(), c.getPhoneNo(), c.getStreetAddress(), c.getZipcode(), c.getCity(), r, total, items);
            c.addOrder(o);
            return true;
        }
        return false;
    }

    public boolean createNoneAccountOrder(String first_name, String last_name, String phone_no, String streetAddress, String zipcode, String city, Date chosenTime, Restaurant r) {
        orders.add(new Order(chosenTime, first_name, last_name, phone_no, streetAddress, zipcode, city, r, total, items));
        return true;
    }

    public void addCustomerRating(Customer customer, Courier courier, Integer rate) {
        getCustomer(customer).addRating(rate, courier.getFirstName());
    }

    public void addCourierRating(Customer customer, Courier courier, Integer rate) {
        getCourier(customer).addRating(rate, customer.getFirstName());
    }

    //private methods
    private Customer getCustomer(Account c) {
        for (Customer customer :
                customers) {
            if (c.getEmail().equals(customer.getEmail())) return customer;
        }
        return null;
    }

    private Courier getCourier(Account c) {
        for (Courier courier :
                couriers) {
            if (c.getEmail().equals(courier.getEmail())) return courier;
        }
        return null;
    }
}
