package edu.icet.controller;

import edu.icet.db.DBConnection;
import edu.icet.model.Customer;

public class UpdateCustomerController {

    private UpdateCustomerController(){}

    static void updateCustomer(Customer selectedCustomer){
        DBConnection.getInstance().getConnection().forEach(customer->{
            if (customer.getId().equals(selectedCustomer.getId())){
                customer.setTitle(selectedCustomer.getTitle());
                customer.setName(selectedCustomer.getName());
                customer.setFullName(selectedCustomer.getFullName());
                customer.setAddress(selectedCustomer.getAddress());
                customer.setDob(selectedCustomer.getDob());
                System.out.println("Updated Successfully!");
                System.out.println("----------------------------------------");
                System.out.println();
            }
        });
    }

}

