/*
    Group - D4
    Hackathon: Vivek Kolhe (86930), Vikrant Gupta (87119)
*/

package com.sunbeam.tester;

import com.sunbeam.entities.Customer;
import com.sunbeam.entities.Item;
import com.sunbeam.wrappers.Admin;
import com.sunbeam.wrappers.Authenticate;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    // to store currently logged-in user
    private static Customer currentUser;
    private static List<Item> arr = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        topMenu(sc);
        sc.close();
    }

    public static void topMenu(Scanner sc) {
        int choice = -1;

        do {
            System.out.println("(0) Exit");
            System.out.println("(1) Sign in!");
            System.out.println("(2) Sign in as admin!");
            System.out.println("(3) Sign up!");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 0:
                    System.out.println("Exiting...");
                    return;
                case 1:
                    currentUser = Authenticate.login(sc, false);
                    if (currentUser == null)
                        continue;
                    customerMenu(sc);
                    break;
                case 2:
                    currentUser = Authenticate.login(sc, true);
                    if (currentUser == null)
                        continue;
                    adminMenu(sc);
                    break;
                case 3:
                    Authenticate.signup(sc);
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
            System.out.println("-----------------");
        } while (choice != 0);
    }

    public static void adminMenu(Scanner sc) {
        int choice;

        do {
            System.out.println("(0) Exit");
            System.out.println("(1) Show all orders");
            System.out.println("(2) Show order details");
            System.out.println("(3) Sign out!");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 0:
                    System.out.println("Exiting...");
                    System.exit(0);
                case 1:
                    Admin.getOrders();
                    break;
                case 2:
                    Admin.orderByID(sc);
                    break;
                case 3:
                    System.out.println("Logged out!");
                    currentUser = null;
                    return;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
            System.out.println("----------------");
        } while (choice != 0);
    }

    public static void customerMenu(Scanner sc) {
        int choice;

        do {
            System.out.println("(0) Exit");
            System.out.println("(1) Show Veg Menu");
            System.out.println("(2) Show Non-Veg Menu");
            System.out.println("(3) Show available sizes");
            System.out.println("(4) Add to cart");
            System.out.println("(5) Show cart");
            System.out.println("(6) Place order");
            System.out.println("(7) Sign out!");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 0:
                    System.out.println("Exiting...");
                    System.exit(0);
                case 1:
                    com.sunbeam.wrappers.Customer.showVeg();
                    break;
                case 2:
                    com.sunbeam.wrappers.Customer.showNonVeg();
                    break;
                case 3:
                    com.sunbeam.wrappers.Customer.showSizes(sc);
                    break;
                case 4:
                    com.sunbeam.wrappers.Customer.addToCart(sc, arr);
                    break;
                case 5:
                    com.sunbeam.wrappers.Customer.showCart(arr);
                    break;
                case 6:
                    com.sunbeam.wrappers.Customer.placeOrder(currentUser, arr);
                    arr.clear();
                    break;
                case 7:
                    System.out.println("Logged out!");
                    currentUser = null;
                    arr.clear();
                    return;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
            System.out.println("-----------------------------");
        } while (choice != 0);
    }
}
