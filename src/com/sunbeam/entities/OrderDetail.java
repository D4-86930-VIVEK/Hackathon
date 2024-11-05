package com.sunbeam.entities;

public class OrderDetail {
    // fields
    private int id, orderId, priceId;

    // constructors
    public OrderDetail() {
    }

    public OrderDetail(int orderId, int priceId) {
        this.orderId = orderId;
        this.priceId = priceId;
    }

    public OrderDetail(int id, int orderId, int priceId) {
        this.id = id;
        this.orderId = orderId;
        this.priceId = priceId;
    }

    // setters and getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getPriceId() {
        return priceId;
    }

    public void setPriceId(int priceId) {
        this.priceId = priceId;
    }

    // method for printing
    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", priceId=" + priceId +
                '}';
    }
}
