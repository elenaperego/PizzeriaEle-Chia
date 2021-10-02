package Classes.Customer;

public class Customer {
    private final long customerId;
    private final String customerName;
    private final long phoneNumber;
    private final String addressStreet;
    private final long addressCode;


    public Customer(long id, String name, long number, String street, long code) {
        this.customerId = id;
        this.customerName = name;
        this.phoneNumber = number;
        this.addressStreet = street;
        this.addressCode = code;
    }

    public long getId() {
        return this.customerId;
    }

    public String getName() { return this.customerName; }

    public long getPhoneNumber() { return this.phoneNumber; }

    public String getAddressStreet() { return this.addressStreet; }

    public long getAddressCode() { return this.addressCode; }
}
