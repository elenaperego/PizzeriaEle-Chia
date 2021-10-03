package Classes.Order;

import Classes.Pizza.Pizza;

import java.time.Clock;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;

public class Order {

    private final int orderId;
    private final int customerId;
    private String orderStatus;
    private final int codeId;
    private double totalPrice;
    private Date estimatedDeliveryTime;

    public Order(int orderId, int customerId, String status, int codeId, Date time, double price) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.codeId = codeId;
        this.orderStatus = status;
        this.totalPrice = price;
        this.estimatedDeliveryTime = time;
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

    public void setStatus(String status){
        this.orderStatus = status;
    }

    public int getCodeId() { return this.codeId; }

    public double getTotalPrice() { return this.totalPrice; }

    public void setTotalPrice(double price) { this.totalPrice = price; }

    public Date getEstimatedDeliveryTime() { return this.estimatedDeliveryTime; }

    public void setEstimatedDeliveryTime(Date time) { this.estimatedDeliveryTime = time; }

}
