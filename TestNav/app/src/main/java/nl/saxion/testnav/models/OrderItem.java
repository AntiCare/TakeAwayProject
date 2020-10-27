package nl.saxion.testnav.models;

import java.util.HashMap;
import java.util.Map;

public class OrderItem {
    String RestaurantName;
    String itemNum;
    String itemQuantity;

    public OrderItem() {

    }
    public OrderItem(String RestaurantName, String itemNum, String itemQuantity) {
        this.itemNum=itemNum;
        this.itemQuantity=itemQuantity;
        this.RestaurantName=RestaurantName;
    }

    public String getRestaurantName() {
        return RestaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        RestaurantName = restaurantName;
    }

    public String getItemNum() {
        return itemNum;
    }

    public void setItemNum(String itemNum) {
        this.itemNum = itemNum;
    }

    public String getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "RestaurantName='" + RestaurantName + '\'' +
                ", itemNum='" + itemNum + '\'' +
                ", itemQuantity='" + itemQuantity + '\'' +
                '}';
    }

    public Map<String,Object> toMap(){
        Map<String,Object> result = new HashMap<>();
        result.put("RestaurantName",RestaurantName);
        result.put("itemNum",itemNum);
        result.put("itemQuantity",itemQuantity);
        return result;
    }


}
