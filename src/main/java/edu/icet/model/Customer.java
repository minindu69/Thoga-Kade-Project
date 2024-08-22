package edu.icet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
public class Customer {
    private String id;
    private String title;
    private String name;
    private String fullName;
    private String address;
    private LocalDate dob;

    public Customer(String id, String title, String name, String address, LocalDate dob) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.fullName = title+" "+name;
        this.address = address;
        this.dob = dob;
    }

}
