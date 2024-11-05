package com.sunbeam.dao;

import com.sunbeam.entities.Customer;
import com.sunbeam.utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao implements AutoCloseable {
    private Connection conn = null;

    public LoginDao() throws SQLException {
        conn = DbUtils.getConnection();
    }

    // add a new customer
    public boolean addCustomer(Customer c) throws SQLException {
        String sql = "INSERT INTO pizza_customers(Name, Password, Mobile, Address, Email) VALUES(?, ?, ?, ?, ?)";

        try (PreparedStatement addStmt = conn.prepareStatement(sql)) {
            addStmt.setString(1, c.getName());
            addStmt.setString(2, c.getPassword());
            addStmt.setString(3, c.getMobile());
            addStmt.setString(4, c.getAddress());
            addStmt.setString(5, c.getEmail());

            int cnt = addStmt.executeUpdate();
            if (cnt > 0)
                return true;
        }
        return false;
    }

    // search user using email
    public Customer getByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM pizza_customers WHERE Email = ?";
        Customer customer = null;

        try (PreparedStatement getStmt = conn.prepareStatement(sql)) {
            getStmt.setString(1, email);
            ResultSet res = getStmt.executeQuery();

            if (!res.next())
                return null;

            customer = new Customer(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6));
        }
        return customer;
    }

    // search user using ID
    public Customer getByID(int id) throws SQLException {
        String sql = "SELECT * FROM pizza_customers WHERE ID = ?";
        Customer customer = null;

        try (PreparedStatement getStmt = conn.prepareStatement(sql)) {
            getStmt.setInt(1, id);
            ResultSet res = getStmt.executeQuery();

            if (!res.next())
                return null;

            customer = new Customer(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6));
        }
        return customer;
    }

    @Override
    public void close() throws Exception {
        if (conn != null)
            conn.close();
    }
}
