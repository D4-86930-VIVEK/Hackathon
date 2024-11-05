package com.sunbeam.entities;

import java.sql.Date;

public class Order {
    // fields
    private int id, customerId;
    private String status;
    // association with sql.Date and OrderDetail
    private java.sql.Date orderDate;
    private OrderDetail orderDetail = null;

    // constructors
    public Order() {
    }

    public Order(int customerId, String status, java.sql.Date orderDate) {
        this.customerId = customerId;
        this.status = status;
        this.orderDate = orderDate;
    }

    public Order(int id, int customerId, String status, Date orderDate) {
        this.id = id;
        this.customerId = customerId;
        this.status = status;
        this.orderDate = orderDate;
    }

    // setters and getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public java.sql.Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(java.sql.Date orderDate) {
        this.orderDate = orderDate;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }

    // method for printing
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", status='" + status + '\'' +
                ", orderDate=" + orderDate +
                ", orderDetail=" + orderDetail +
                '}';
    }
}
