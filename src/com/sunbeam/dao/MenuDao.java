package com.sunbeam.dao;

import com.sunbeam.entities.Item;
import com.sunbeam.entities.Price;
import com.sunbeam.utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuDao implements AutoCloseable {
    private Connection conn = null;

    public MenuDao() throws SQLException {
        conn = DbUtils.getConnection();
    }

    // get all available categories
    public List<String> getCategories() throws SQLException {
        String sql = "SELECT DISTINCT(Type) FROM pizza_items";
        List<String> arr = new ArrayList<>();

        try (PreparedStatement getStmt = conn.prepareStatement(sql)) {
            ResultSet res = getStmt.executeQuery();

            while (res.next())
                arr.add(res.getString(1));
        }
        return arr;
    }

    // get all available sub-categories (Veg)
    public List<String> getVegSubCategories() throws SQLException {
        String sql = "SELECT DISTINCT(Category) FROM pizza_items WHERE Type = 'Veg'";
        List<String> arr = new ArrayList<>();

        try (PreparedStatement getStmt = conn.prepareStatement(sql)) {
            ResultSet res = getStmt.executeQuery();

            while (res.next())
                arr.add(res.getString(1));
        }
        return arr;
    }

    // get all available categories (Non-Veg)
    public List<String> getNonVegSubCategories() throws SQLException {
        String sql = "SELECT DISTINCT(Category) FROM pizza_items WHERE Type = 'NonVeg'";
        List<String> arr = new ArrayList<>();

        try (PreparedStatement getStmt = conn.prepareStatement(sql)) {
            ResultSet res = getStmt.executeQuery();

            while (res.next())
                arr.add(res.getString(1));
        }
        return arr;
    }

    // get all available categories
    public List<String> getAllSubCategories() throws SQLException {
        String sql = "SELECT DISTINCT(Category) FROM pizza_items";
        List<String> arr = new ArrayList<>();

        try (PreparedStatement getStmt = conn.prepareStatement(sql)) {
            ResultSet res = getStmt.executeQuery();

            while (res.next())
                arr.add(res.getString(1));
        }
        return arr;
    }

    // get all Veg items from the menu
    public List<Item> getVegItems() throws SQLException {
        String sql = "SELECT * FROM pizza_items WHERE Type = 'Veg'";
        List<Item> arr = new ArrayList<>();

        try (PreparedStatement getStmt = conn.prepareStatement(sql)) {
            ResultSet res = getStmt.executeQuery();

            while (res.next()) {
                Item item = new Item(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5));
                arr.add(item);
            }
        }
        return arr;
    }

    // get all Non-Veg items from the menu
    public List<Item> getNonVegItems() throws SQLException {
        String sql = "SELECT * FROM pizza_items WHERE Type = 'NonVeg'";
        List<Item> arr = new ArrayList<>();

        try (PreparedStatement getStmt = conn.prepareStatement(sql)) {
            ResultSet res = getStmt.executeQuery();

            while (res.next()) {
                Item item = new Item(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5));
                arr.add(item);
            }
        }
        return arr;
    }

    // search using category and sub-category
    public List<Item> getByCatAndSubCat(String cat, String subCat) throws SQLException {
        String sql = "SELECT * FROM pizza_items, pizza_pricing WHERE pizza_items.ID = pizza_pricing.ITEMID AND Type = ? AND Category = ?";
        List<Item> arr = new ArrayList<>();

        try (PreparedStatement getStmt = conn.prepareStatement(sql)) {
            getStmt.setString(1, cat);
            getStmt.setString(2, subCat);
            ResultSet res = getStmt.executeQuery();

            while (res.next()) {
                Item item = new Item(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5));
                item.setPrice(new Price(res.getInt(6), res.getInt(7), res.getDouble(9), res.getString(8)));
                arr.add(item);
            }
        }
        return arr;
    }

    // search using ID of an item
    public List<Item> getByID(int id) throws SQLException {
        String sql = "SELECT * FROM pizza_items, pizza_pricing WHERE pizza_pricing.ITEMID = ? AND pizza_items.ID = pizza_pricing.ITEMID";
        List<Item> arr = new ArrayList<>();

        try (PreparedStatement getStmt = conn.prepareStatement(sql)) {
            getStmt.setInt(1, id);
            ResultSet res = getStmt.executeQuery();

            while (res.next()) {
                Item item = new Item(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5));
                item.setPrice(new Price(res.getInt(6), res.getInt(7), res.getDouble(9), res.getString(8)));
                arr.add(item);
            }
        }
        return arr;
    }

    // search using PriceID
    public Item getPriceByPriceID(int id) throws SQLException {
        String sql = "SELECT * FROM pizza_items, pizza_pricing WHERE pizza_pricing.ID = ? AND pizza_items.ID = pizza_pricing.ITEMID";
        Item item = null;

        try (PreparedStatement getStmt = conn.prepareStatement(sql)) {
            getStmt.setInt(1, id);
            ResultSet res = getStmt.executeQuery();

            if (!res.next())
                return null;

            item = new Item(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5));
            item.setPrice(new Price(res.getInt(6), res.getInt(7), res.getDouble(9), res.getString(8)));
        }
        return item;
    }

    @Override
    public void close() throws Exception {
        if (conn != null)
            conn.close();
    }
}
