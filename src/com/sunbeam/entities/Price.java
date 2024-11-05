package com.sunbeam.entities;

public class Price {
    // fields
    private int id, itemId;
    private double price;
    private String size;

    // constructors
    public Price() {
        this(-1, -1, 0, "");
    }

    public Price(int itemId, double price, String size) {
        this.itemId = itemId;
        this.price = price;
        this.size = size;
    }

    public Price(int id, int itemId, double price, String size) {
        this.id = id;
        this.itemId = itemId;
        this.price = price;
        this.size = size;
    }

    // setters and getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    // method for printing
    @Override
    public String toString() {
        return "Price{" +
                "id=" + id +
                ", itemId=" + itemId +
                ", price=" + price +
                ", size='" + size + '\'' +
                '}';
    }
}
