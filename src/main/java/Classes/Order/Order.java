package Classes.Order;

import java.time.Clock;
import java.time.Duration;

public class Order {
    private final int orderId;
    private final int customerId;
    private String orderStatus;
    private final String codeId;
    private double totalPrice;
    private java.util.Date estimatedDeliveryTime;

    private final String ordered_at;
    private final String delivery_time;
    private final Clock clockOrdered = Clock.systemUTC();
    private final Clock clockDelivered = Clock.offset(clockOrdered, Duration.ofMinutes(20));

    public Order(int orderId, int customerId, String status, String codeId, double price, java.util.Date time) {
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

    public int getCustomerId() {
        return this.customerId;
    }

    public String getStatus() {
        return this.orderStatus;
    }

    public String getCodeId() { return this.codeId; }

    public double getTotalPrice() { return this.totalPrice; }

    public java.util.Date getEstimatedDeliveryTime() { return this.estimatedDeliveryTime; }

    public void setEstimatedDeliveryTime(java.util.Date time) { this.estimatedDeliveryTime = time; }
}
