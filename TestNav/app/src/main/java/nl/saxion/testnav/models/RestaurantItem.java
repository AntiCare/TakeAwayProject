package nl.saxion.testnav.models;

public class RestaurantItem {
private String itemName;
private String description;
private int price;

public RestaurantItem (String itemName,String description,int price) {
    this.description=description;
    this.itemName=itemName;
    this.price=price;
}

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
