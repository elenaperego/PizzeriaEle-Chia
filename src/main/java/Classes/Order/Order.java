package Classes.Order;

import Classes.Pizza.Pizza;

import java.time.Clock;
import java.time.Duration;
import java.util.ArrayList;

public class Order {
    private final long id;
    private final String customer_id;
    private String status;
    private final String ordered_at;
    private final boolean takeaway;
    private final String payment_type;
    private final String delivery_time;
    private final ArrayList<String> delivery_address;
    private final ArrayList<Pizza> pizzas;
    private final Clock clockOrdered = Clock.systemUTC();
    private final Clock clockDelivered = Clock.offset(clockOrdered, Duration.ofMinutes(20));

    public Order(long id, String customer_id, String status, boolean takeaway, String payment_type, ArrayList<String> delivery_address, ArrayList<Pizza> pizzas) {
        this.id = id;
        this.customer_id = customer_id;
        this.status = status;
        this.takeaway = takeaway;
        this.payment_type = payment_type;
        this.delivery_address = delivery_address;
        this.pizzas = pizzas;
        this.ordered_at = clockOrdered.instant().toString();
        this.delivery_time = clockDelivered.instant().toString();
    }

    public long getId() {
        return id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getOrdered_at() {
        return ordered_at;
    }

    public boolean isTakeaway() {
        return takeaway;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public String getDelivery_time() {
        return delivery_time;
    }

    public ArrayList<String> getDelivery_address() {
        return delivery_address;
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

}
