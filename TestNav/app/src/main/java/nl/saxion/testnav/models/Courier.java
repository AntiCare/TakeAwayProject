package nl.saxion.testnav.models;

import java.util.Calendar;
import java.util.List;

public class Courier extends Account {
    //fields
    private String licensePlate, licenseNo;
    private List<OrderItem> orders;

    //ctor
    public Courier(String email, String password, String first_name, String last_name, String phone_no) {
        super(email, password, first_name, last_name, phone_no);
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public List<OrderItem> getAllOrders(){
        return this.orders;
    }


}
