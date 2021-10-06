package Classes.Customer;

import Mappers.ConnectionImpl;
import Mappers.DataMapper;
import Mappers.OrderDataMapper;

import java.sql.*;

public class Customer {
    private final int customerId;
    private final String customerName;
    private final String phoneNumber;
    private final String addressStreet;
    private final int addressCode;
    private int orderedPizzas = 0;

    public Customer(int id, String name, String number, String street, int code) {
        this.customerId = id;
        this.customerName = name;
        this.phoneNumber = number;
        this.addressStreet = street;
        this.addressCode = code;
    }

    public int getId() {
        return this.customerId;
    }

    public String getName() { return this.customerName; }

    public String getPhoneNumber() { return this.phoneNumber; }

    public String getAddressStreet() { return this.addressStreet; }

    public int getAddressCode() { return this.addressCode; }

    // n is the number of pizza ordered during a new order
    public void addOrderedPizzas(int n) { this.orderedPizzas += n; }

    public void setOrderedPizzas(int n) { this.orderedPizzas = n; }

    public int getOrderedPizzas() { return this.orderedPizzas; }
}
