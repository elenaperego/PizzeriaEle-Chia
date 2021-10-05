package Classes.Order;

import java.sql.Date;
import java.time.Clock;
import java.time.Duration;

public class Order {
    private final long orderId;
    private final long customerId;
    private String orderStatus;
    private final long codeId;
    private double totalPrice;
    private Date estimatedDeliveryTime;

    private final String ordered_at;
    private final String delivery_time;
    private final Clock clockOrdered = Clock.systemUTC();
    private final Clock clockDelivered = Clock.offset(clockOrdered, Duration.ofMinutes(20));

    public Order(long orderId, long customerId, String status, long codeId, double price, Date time) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.codeId = codeId;
        this.orderStatus = status;
        this.totalPrice = price;
        this.estimatedDeliveryTime = time;

        this.ordered_at = clockOrdered.instant().toString();
        this.delivery_time = clockDelivered.instant().toString();
    }

    public long getId() {
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

    public Date getEstimatedDeliveryTime() { return this.estimatedDeliveryTime; }

    public void setEstimatedDeliveryTime(Date time) { this.estimatedDeliveryTime = time; }

    public String getOrdered_at() {
        return ordered_at;
    }

    public String getDelivery_time() {
        return delivery_time;
    }

}
