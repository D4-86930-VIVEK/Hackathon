package com.sunbeam.wrappers;

import com.sunbeam.dao.MenuDao;
import com.sunbeam.dao.OrderDao;
import com.sunbeam.entities.Item;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

// class with wrapper functions for operations that can be performed by a customer
public class Customer {
    public static void showVeg() {
        try (MenuDao dao = new MenuDao()) {
            List<Item> arr = dao.getVegItems();
            for (Item item : arr)
                System.out.println(item.printItem());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void showNonVeg() {
        try (MenuDao dao = new MenuDao()) {
            List<Item> arr = dao.getNonVegItems();
            for (Item item : arr)
                System.out.println(item.printItem());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void showSizes(Scanner sc) {
        int id;

        System.out.print("Enter ID to show available sizes: ");
        id = sc.nextInt();

        try (MenuDao dao = new MenuDao()) {
            List<Item> arr = dao.getByID(id);
            for (Item item : arr)
                System.out.println(item);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void addToCart(Scanner sc, List<Item> arr) {
        int id;

        System.out.print("Enter price ID to add: ");
        id = sc.nextInt();

        try (MenuDao dao = new MenuDao()) {
            Item item = dao.getPriceByPriceID(id);

            if (item == null) {
                System.out.println("Item does not exist!");
                return;
            }

            arr.add(item);
            System.out.println("Added to cart!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void showCart(List<Item> arr) {
        if (arr.isEmpty()) {
            System.out.println("Cart is currently empty!");
            return;
        }

        System.out.println("Items in the cart");
        for (Item e : arr) {
            System.out.println(e);
        }
    }

    public static void placeOrder(com.sunbeam.entities.Customer c, List<Item> arr) {
        try (OrderDao dao = new OrderDao()) {
            if (!dao.addOrder(c.getId(), arr)) {
                System.out.println("Failed to place order!");
                return;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Order placed!");
    }
}
