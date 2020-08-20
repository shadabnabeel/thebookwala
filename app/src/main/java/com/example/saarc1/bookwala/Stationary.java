package com.example.saarc1.bookwala;


public class Stationary {

    private String userId;
    private String itemId;
    private String itemName;
    private String itemPrice;
    private String itemCategory;
    private String itemImage;
    private String totalPrice;
    private String quantity;


    public Stationary(){

    }

    public Stationary(String userId , String itemName, String itemPrice, String itemCategory, String itemImage, String itemId, String totalPrice, String quantity) {
        this.userId = userId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCategory = itemCategory;
        this.itemId = itemId;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
        this.itemImage = itemImage;
    }

    public String getUserId() { return userId; }

    public String getItemId() { return itemId; }

    public String getItemName() { return itemName; }

    public String getItemPrice() { return itemPrice; }

    public String getItemAuthor() {
        return itemCategory;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }
}
