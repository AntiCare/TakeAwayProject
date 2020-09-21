package nl.saxion.testnav.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Restaurant {
    private static int id = 0;
    private String name, streetAddress, zipcode, city, phoneNo, items;
    private List<String> cuisines;
    private HashMap<Integer, String> ratings;

    //ctor
    public Restaurant(String name, String streetAddress, String zipcode, String city, String phoneNo) {
        this.name = name;
        this.streetAddress = streetAddress;
        this.zipcode = zipcode;
        this.city = city;
        this.phoneNo = phoneNo;
        this.cuisines = new ArrayList<>();
        this.ratings = new HashMap<>();
    }

    //#region getters setters
    public String getName() {
        return name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public double getAvgRating(){
        double avg = 0;
        for (Map.Entry<Integer, String> entry : ratings.entrySet()) {
            int key = entry.getKey();
            avg += key;
        }
        avg = avg / ratings.size();
        return avg;
    }

    public List<String> getCuisines() {
        return cuisines;
    }

    public HashMap<Integer, String> getRatings() {
        return ratings;
    }

    public void addCuisines(Object obj) {
        if(obj instanceof String){
            this.cuisines.add((String) obj);
        } else if(obj instanceof List){
            for (Object s : (List)obj) {
                this.cuisines.add((String)s);
            }
        }
    }



    //#endregion

    //methods
    @Override
    public String toString() {
        return "name: " + this.name + "\taddress: " + this.streetAddress + " " + this.zipcode + " "
                + this.city + "\t phone no: " + this.phoneNo + "\trating: " + this.getAvgRating();
    }
}

