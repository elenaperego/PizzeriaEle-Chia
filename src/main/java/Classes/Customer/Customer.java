package Classes.Customer;

import java.util.concurrent.atomic.AtomicInteger;

public class Customer {
    private final int customerId;
    private final String customerName;
    private final String phoneNumber;
    private final String addressStreet;
    private final String addressCode;
    private int orderedPizzas;      // Parameter added to check pizzas (at least 10 to get discount code)
    private static AtomicInteger counter = new AtomicInteger(0);

    public Customer(int id, String name, String number, String street, String code, int orderedPizzas) {
        this.customerId = counter.incrementAndGet();
        this.customerName = name;
        this.phoneNumber = number;
        this.addressStreet = street;
        this.addressCode = code;
        this.orderedPizzas = orderedPizzas;
    }

    public int getId() {
        return this.customerId;
    }

    public String getName() { return this.customerName; }

    public String getPhoneNumber() { return this.phoneNumber; }

    public String getAddressStreet() { return this.addressStreet; }

    public String getAddressCode() { return this.addressCode; }

    public int getOrderedPizzas() { return this.orderedPizzas; }
}
