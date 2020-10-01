package nl.saxion.testnav.models;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Account {
    //fields
    private static int id = 0;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNo;
    private ACCOUNT_STATUS status;
    private List<Order> allOrders;
    private String imageURL;

    private HashMap<Integer, String> ratings;

    public Account(String email, String password, String firstName, String lastName, String phoneNo) {
        id++;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.ratings = new HashMap<>();
        this.imageURL = "default";
    }

    public Account(String firstName, String lastName, String phoneNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
    }

    public ACCOUNT_STATUS getStatus() {
        return status;
    }

    //#region getters setters
    public void setStatus(ACCOUNT_STATUS status) {
        this.status = status;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public double getAvgRating() {
        double avg = 0;
        for (Map.Entry<Integer, String> entry : ratings.entrySet()) {
            int key = entry.getKey();
            avg += key;
        }
        avg = avg / ratings.size();
        return avg;
    }
    //#endregion

    //methods
    public void sendMessage(Order o, String message) {
        String m = "[" + this.firstName + " - " + Calendar.getInstance() + "] - " + message;
        o.addMessage(m);
    }

    public void addRating(Integer rate, String firstName) {
        this.ratings.put(rate, firstName);
    }

}
