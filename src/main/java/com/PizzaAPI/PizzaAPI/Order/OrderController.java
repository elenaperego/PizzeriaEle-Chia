package com.PizzaAPI.PizzaAPI.Order;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import com.PizzaAPI.PizzaAPI.Order.Order;
import com.PizzaAPI.PizzaAPI.Pizza.Pizza;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    private final AtomicLong counter = new AtomicLong();
    private final ArrayList<Order> orders = new ArrayList<>();

    @GetMapping("/order/{customer_id}")
    public ArrayList<Order> getOrders(@RequestParam(value = "customerID", defaultValue = "-1") String id){
        ArrayList<Order> ordersCustomer = new ArrayList<>();
        boolean found = false;
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getCustomer_id().equals(id)) {
                ordersCustomer.add(orders.get(i));
                found = true;
            }
        }
        return ordersCustomer;
    }

    @PostMapping("/order")
    public Order submitOrder(@RequestParam(value = "pizzas", defaultValue = "-1") ArrayList<Pizza> pizzas, @RequestParam(value = "takeAway", defaultValue = "-1") boolean takeaway, @RequestParam(value = "paymentType", defaultValue = "-1")String payment_type, @RequestParam(value = "customerID", defaultValue = "-1")String customer_id, @RequestParam(value = "deliveryAddress", defaultValue = "-1")ArrayList<String>delivery_address){
        return new Order(counter.incrementAndGet(), customer_id, "In Progress", takeaway, payment_type, delivery_address, pizzas);
    }

    @PostMapping("/save")
    public ResponseEntity<Order> addEmployee(@RequestParam(value = "pizzas", defaultValue = "-1") ArrayList<Pizza> pizzas, @RequestParam(value = "takeAway", defaultValue = "-1") boolean takeaway, @RequestParam(value = "paymentType", defaultValue = "-1")String payment_type, @RequestParam(value = "customerID", defaultValue = "-1")String customer_id, @RequestParam(value = "deliveryAddress", defaultValue = "-1")ArrayList<String>delivery_address){
        return new ResponseEntity<Order>(new Order(counter.incrementAndGet(), customer_id, "In Progress", takeaway, payment_type, delivery_address, pizzas), HttpStatus.CREATED);
    }

    @PutMapping("/order/cancel/{order_id}")
    public Order cancelOrder(@RequestParam(value = "orderID", defaultValue = "-1") long orderID){
        Order order = null;
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId() == orderID) {
                orders.get(i).setStatus("cancelled");
                order = orders.get(i);
            }
        }
        return order;
    }

    @GetMapping("/order/deliverytime/{order_id}")
    public Order getDeliveryTime(@RequestParam(value = "orderID", defaultValue = "-1") long orderID){
        Order order = null;
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId() == orderID) {
                order = orders.get(i);
            }
        }
        return order;
    }
}
