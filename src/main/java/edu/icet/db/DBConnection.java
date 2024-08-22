package edu.icet.db;

import edu.icet.model.Customer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {
    private static DBConnection instance;
    private final List<Customer> customerList;

    private DBConnection(){
        this.customerList = new ArrayList<>();
        customerList.add(new Customer("001","Mr. ","Nimal","Panadura", LocalDate.parse("2024-08-10")));
        customerList.add(new Customer("002","Miss. ","Kamal","Panadura", LocalDate.parse("2024-08-10")));
        customerList.add(new Customer("003","Mr. ","Sanoj","Panadura", LocalDate.parse("2024-07-15")));
        customerList.add(new Customer("004","Miss. ","Sandun","Panadura", LocalDate.parse("2024-08-10")));
        customerList.add(new Customer("005","Miss. ","Sandun","Panadura", LocalDate.parse("2024-08-10")));
        customerList.add(new Customer("006","Miss. ","Sandun","Panadura", LocalDate.parse("2024-08-10")));
        customerList.add(new Customer("007","Miss. ","Sandun","Panadura", LocalDate.parse("2024-08-10")));
        customerList.add(new Customer("008","Miss. ","Sandun","Panadura", LocalDate.parse("2024-08-10")));
        customerList.add(new Customer("009","Miss. ","Sandun","Panadura", LocalDate.parse("2024-08-10")));
        customerList.add(new Customer("010","Miss. ","Sandun","Panadura", LocalDate.parse("2024-08-10")));
        customerList.add(new Customer("011","Miss. ","Sandun","Panadura", LocalDate.parse("2024-08-10")));
        customerList.add(new Customer("012","Miss. ","Sandun","Panadura", LocalDate.parse("2024-08-10")));
        System.out.println(Integer.toHexString(System.identityHashCode(customerList))+" --> DB connected successfully");
    }

    public static DBConnection getInstance(){
        return null==instance?instance = new DBConnection():instance;
    }

    public List<Customer> getConnection(){
        return customerList;
    }

}


