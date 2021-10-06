package Classes.Order;

import Classes.Pizza.Pizza;
import Mappers.ConnectionImpl;

import java.sql.*;
import java.time.Clock;
import java.time.Duration;

public class Order {
    private final int orderId;
    private final int customerId;
    private String orderStatus;
    private final long codeId;
    private double totalPrice;
    private java.util.Date estimatedDeliveryTime;
    private int numberPizzas;

    private final String ordered_at;
    private final String delivery_time;
    private final Clock clockOrdered = Clock.systemUTC();
    private final Clock clockDelivered = Clock.offset(clockOrdered, Duration.ofMinutes(20));

    public Order(int orderId, int customerId, String status, long codeId, double price, java.util.Date time) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.codeId = codeId;
        this.orderStatus = status;
        this.totalPrice = price;
        this.estimatedDeliveryTime = time;

        this.ordered_at = clockOrdered.instant().toString();
        this.delivery_time = clockDelivered.instant().toString();
    }

    public int getId() {
        return this.orderId;
    }

    public long getCustomerId() {
        return this.customerId;
    }

    public String getStatus() {
        return this.orderStatus;
    }

    public void setStatus(String status){
        this.orderStatus = status;
    }

    public long getCodeId() { return this.codeId; }

    public double getTotalPrice() { return this.totalPrice; }

    public void setTotalPrice(double price) { this.totalPrice = price; }

    public java.util.Date getEstimatedDeliveryTime() { return this.estimatedDeliveryTime; }

    public void setEstimatedDeliveryTime(java.util.Date time) { this.estimatedDeliveryTime = time; }

    public String getOrdered_at() {
        return ordered_at;
    }

    public String getDelivery_time() {
        return delivery_time;
    }


}
