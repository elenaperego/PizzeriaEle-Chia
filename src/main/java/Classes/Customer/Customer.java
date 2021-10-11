package Classes.Customer;

public class Customer {
    private final int customerId;
    private final String customerName;
    private final String phoneNumber;
    private final String addressStreet;
    private final int addressCode;
    private int orderedPizzas;      // Parameter added to check pizzas (at least 10 to get discount code)
    public Customer(int id, String name, String number, String street, int code, int orderedPizzas) {
        this.customerId = id;
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

    public int getAddressCode() { return this.addressCode; }

    public int getOrderedPizzas() { return this.orderedPizzas; }

    // n is the number of pizza ordered during a new order
    //public void addOrderedPizzas(int n) { this.orderedPizzas += n; }

    //public void setOrderedPizzas(int n) { this.orderedPizzas = n; }
}
