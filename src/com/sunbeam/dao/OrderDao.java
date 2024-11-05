package com.sunbeam.dao;

import com.sunbeam.entities.Item;
import com.sunbeam.entities.Order;
import com.sunbeam.entities.OrderDetail;
import com.sunbeam.utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao implements AutoCloseable {
    private Connection conn = null;

    public OrderDao() throws SQLException {
        conn = DbUtils.getConnection();
    }

    // get all orders
    public List<Order> getOrders() throws SQLException {
        String sql = "SELECT * FROM pizza_orders, pizza_orderdetails WHERE pizza_orders.ID = pizza_orderdetails.OrderId ORDER BY pizza_orders.OrderTime DESC";
        List<Order> arr = new ArrayList<>();

        try (PreparedStatement getStmt = conn.prepareStatement(sql)) {
            ResultSet res = getStmt.executeQuery();

            while (res.next()) {
                Order o = new Order(res.getInt(1), res.getInt(2), res.getString(4), res.getDate(3));
                o.setOrderDetail(new OrderDetail(res.getInt(5), res.getInt(6), res.getInt(7)));
                arr.add(o);
            }
        }
        return arr;
    }

    // get an order by ID
    public List<Order> getOrderByID(int id) throws SQLException {
        String sql = "SELECT * FROM pizza_orders, pizza_orderdetails WHERE pizza_orders.ID = ? AND pizza_orders.ID = pizza_orderdetails.OrderId";
        List<Order> arr = new ArrayList<>();

        try (PreparedStatement getStmt = conn.prepareStatement(sql)) {
            getStmt.setInt(1, id);
            ResultSet res = getStmt.executeQuery();

            while (res.next()) {
                Order o = new Order(res.getInt(1), res.getInt(2), res.getString(4), res.getDate(3));
                o.setOrderDetail(new OrderDetail(res.getInt(5), res.getInt(6), res.getInt(7)));
                arr.add(o);
            }
        }
        return arr;
    }

    // add an order with all its items
    public boolean addOrder(int id, List<Item> arr) throws SQLException {
        String sql = "INSERT INTO pizza_orders(CustomerId, OrderTime, STATUS) VALUES(?, CURRENT_TIMESTAMP, 'Pending')";

        try (PreparedStatement addStmt = conn.prepareStatement(sql)) {
            addStmt.setInt(1, id);

            int cnt = addStmt.executeUpdate();
            if (cnt < 1)
                return false;
        }

        int maxID;
        sql = "SELECT MAX(ID) FROM pizza_orders";

        try (PreparedStatement getStmt = conn.prepareStatement(sql)) {
            ResultSet res = getStmt.executeQuery();

            res.next();
            maxID = res.getInt(1);
        }

        sql = "INSERT INTO pizza_orderdetails(OrderId, PRICEID) VALUES(?, ?)";

        for (Item item : arr) {
            try (PreparedStatement addStmt = conn.prepareStatement(sql)) {
                addStmt.setInt(1, maxID);
                addStmt.setInt(2, item.getPrice().getId());

                addStmt.executeUpdate();
            }
        }
        return true;
    }

    @Override
    public void close() throws Exception {
        if (conn != null)
            conn.close();
    }
}
