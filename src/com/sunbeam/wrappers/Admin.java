package com.sunbeam.wrappers;

import com.sunbeam.dao.LoginDao;
import com.sunbeam.dao.MenuDao;
import com.sunbeam.dao.OrderDao;
import com.sunbeam.entities.Customer;
import com.sunbeam.entities.Item;
import com.sunbeam.entities.Order;

import java.util.List;
import java.util.Scanner;

// class with wrapper functions for operations that can be performed by an admin
public class Admin {
    public static void getOrders() {
        try (OrderDao dao = new OrderDao()) {
            List<Order> arr = dao.getOrders();
            for (Order o : arr)
                System.out.println(o);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void orderByID(Scanner sc) {
        int id;
        System.out.print("Enter order id: ");
        id = sc.nextInt();

        try (OrderDao dao = new OrderDao()) {
            List<Order> arr = dao.getOrderByID(id);
            if (arr.isEmpty()) {
                System.out.println("No orders were found for the given order ID");
                return;
            }

            for (Order o : arr)
                System.out.println(o);

            System.out.println("--------------------------");
            try (LoginDao loginDao = new LoginDao()) {
                Customer c = loginDao.getByID(arr.get(0).getCustomerId());
                System.out.println("Ordered by the following customer");
                System.out.println(c);
            }

            System.out.println("--------------------------");
            System.out.println("Items present in the order");
            try (MenuDao menuDao = new MenuDao()) {
                for (Order o : arr) {
                    Item item = menuDao.getPriceByPriceID(o.getOrderDetail().getPriceId());
                    System.out.println(item.getName() + "\t" + item.getPrice());
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
