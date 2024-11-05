package com.sunbeam.entities;

public class Customer {
    // fields
    private int id;
    private String name, password, mobile, address, email;

    // constructors
    public Customer() {
        this(-1, "", "", "", "", "");
    }

    public Customer(String name, String password, String mobile, String address, String email) {
        this.name = name;
        this.password = password;
        this.mobile = mobile;
        this.address = address;
        this.email = email;
    }

    public Customer(int id, String name, String password, String mobile, String address, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.mobile = mobile;
        this.address = address;
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // method for printing
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
