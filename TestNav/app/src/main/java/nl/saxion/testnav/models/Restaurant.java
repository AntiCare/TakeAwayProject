package nl.saxion.testnav.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Restaurant {
    private String name, streetAddress, zipcode, city;
    private String imageURL;

    public Restaurant() {

    }
    //ctor
    public Restaurant(String name, String streetAddress, String zipcode, String city, String imageURL) {
        this.name = name;
        this.streetAddress = streetAddress;
        this.zipcode = zipcode;
        this.city = city;
        this.imageURL = imageURL;
    }

    //#region getters setters
    public String getImageURL() {
        return imageURL;
    }

    public String getName() {
        return name;
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



//    public double getAvgRating() {
//        double avg = 0;
//        for (Map.Entry<Integer, String> entry : ratings.entrySet()) {
//            int key = entry.getKey();
//            avg += key;
//        }
//        avg = avg / ratings.size();
//        return avg;
//    }



//    public Map<Integer, String> getRatings() {
//        return ratings;
//    }

    //#endregion

    //methods
    @Override
    public String toString() {
        return "name: " + this.name + "\tcuisines: " + "\taddress: " + this.streetAddress + " " + this.zipcode + " "
                + this.city ;
    }

//    public void addRatings(Integer rate, String firstName) {
//        this.ratings.put(rate, firstName);
//    }

    public Map<String,Object> toMap(){
        Map<String,Object> result = new HashMap<>();
        result.put("name",name);
        result.put("streetAddress",streetAddress);
        result.put("zipcode",zipcode);
        result.put("city",city);
        result.put("imageURL",imageURL);
        return result;
    }
}

