package com.sunbeam.wrappers;

import com.sunbeam.dao.LoginDao;
import com.sunbeam.entities.Customer;

import java.sql.SQLException;
import java.util.Scanner;

// class with wrapper functions for authentication
public class Authenticate {
    public static Customer login(Scanner sc, boolean admin) {
        String email, password;

        System.out.print("Enter email: ");
        email = sc.next();
        System.out.print("Enter password: ");
        password = sc.next();

        if (admin && email.compareTo("admin@sunbeaminfo.com") != 0) {
            System.out.println("User is not admin!");
            return null;
        }

        try (LoginDao dao = new LoginDao()) {
            Customer c = dao.getByEmail(email);

            if (c == null) {
                System.out.println("Customer does not exist!");
                return null;
            }

            if (c.getPassword().compareTo(password) == 0) {
                System.out.println("Logged in as: " + c.getName());
                return c;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("Invalid credentials!");
        return null;
    }

    public static void signup(Scanner sc) {
        String name, password, email, address, mobile;

        sc.nextLine();
        System.out.print("Enter name: ");
        name = sc.nextLine();
        System.out.print("Enter email: ");
        email = sc.nextLine();
        System.out.print("Enter password: ");
        password = sc.nextLine();
        System.out.print("Enter address: ");
        address = sc.nextLine();
        System.out.print("Enter mobile number: ");
        mobile = sc.nextLine();

        try (LoginDao dao = new LoginDao()) {
            if (dao.getByEmail(email) != null) {
                System.out.println("Customer with that email already exists!");
                return;
            }

            if (dao.addCustomer(new Customer(name, password, mobile, address, email))) {
                System.out.println("Customer added!");
                return;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Failed to add!");
    }
}
