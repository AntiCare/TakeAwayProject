package nl.saxion.testnav.models;

import java.util.Calendar;

public class Account {
    //fields
    private static int id = 0;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNo;
    private ACCOUNT_STATUS status;

    public Account(String email, String password, String first_name, String last_name, String phone_no) {
        id++;
        this.email = email;
        this.password = password;
        this.firstName = first_name;
        this.lastName = last_name;
        this.phoneNo = phone_no;
    }

    public ACCOUNT_STATUS getStatus() {
        return status;
    }

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

    public void sendMessage(Order o, String message){
        String m = "[" + this.firstName + " - " + Calendar.getInstance() + "] - " + message;
        o.addMessage(m);
    }
}

enum ACCOUNT_STATUS {
   ONLINE, OFFLINE
}
