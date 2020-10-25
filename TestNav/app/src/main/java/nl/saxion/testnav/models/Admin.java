package nl.saxion.testnav.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Admin {
    public static List<Account> accounts;

    public static List<OrderItem> orders;
    public static List<Restaurant> restaurants;

    //TODO: GET RID OF SAMPLE INFO
    //sample info
    private double total = ThreadLocalRandom.current().nextDouble(10, 100);
    private String items = "";

    static {
        accounts = new ArrayList<>();
        orders = new ArrayList<>();
        restaurants = new ArrayList<>();
    }

    public List<OrderItem> getCourierOrders(Courier courier){
        return courier.getAllOrders();
    }
//    public List<OrderItem> getCustomerCurrentOrders(Customer customer){
//        return customer.getCurrentOrders();
//    }

    public static boolean addRestaurant(Restaurant r) {
        if (restaurants.contains(r)) return false;
        else restaurants.add(r);
        return true;
    }

    public static List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public boolean addAccount(Account a) {
        for (Account acc : accounts) {
            if (a.getEmail().equals(acc.getEmail())) {
                return false;
            }
        }
        accounts.add(a);
        return true;
    }


    public boolean createNoneAccountOrder(String RestaurantName, String itemNum, String itemQuantity) {
        orders.add(new OrderItem(RestaurantName,itemNum,itemQuantity));
        return true;
    }

//    public void addCustomerRating(Customer customer, Courier courier, Integer rate) {
//        getAccount(customer).addRating(rate, courier.getFirstName());
//    }
//
//    public void addCourierRating(Customer customer, Courier courier, Integer rate) {
//        getAccount(customer).addRating(rate, customer.getFirstName());
//    }

    private Account getAccount(Account c) {
        for (Account account :
                accounts) {
            if (c.getEmail().equals(account.getEmail())) return c;
        }
        return null;
    }

    public static Account getAccount(String email) {
        for (Account a : accounts) {
            if (a.getEmail().equals(email)) return a;
        }
        return null;
    }

    //TODO: remove after established firebase authentication
    //methods for sample data
    public static boolean checkCredential(String email, String password) {
        for (Account a : accounts) {
            if (a.getEmail().equals(email) && a.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    //if Customer, return true; if Courier, return false;
//    public static boolean checkAccountType(String email) {
//        for (Account a : accounts) {
//            if (a.getEmail().equals(email) && a instanceof Customer) {
//                return true;
//            }
//        }
//        return false;
//    }
}
