package com.sunbeam.entities;

public class Item {
    // fields
    int id;
    String name, type, category, description;
    // association with price
    Price price;

    // constructors
    public Item() {
        this(-1, "", "", "", "");
    }

    public Item(String name, String type, String category, String description) {
        this.name = name;
        this.type = type;
        this.category = category;
        this.description = description;
    }

    public Item(int id, String name, String type, String category, String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.category = category;
        this.description = description;
    }

    // setters and getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    // methods for printing
    public String printItem() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
