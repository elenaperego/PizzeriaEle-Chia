package Mappers;

import Classes.Customer.Customer;

import Classes.DeliveryPerson.DeliveryPerson;
import Visualization.CustomerFrame;
import Visualization.OrderFrame;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class TempMain {

    static AtomicInteger counter = new AtomicInteger();

    public static void main (String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException {
        Connection conn = ConnectionImpl.getConnection();
        DiscountCodeDataMapper codeMapper = new DiscountCodeDataMapper(conn);
        Random random = new Random();
        int num = random.nextInt(100000);
        String discountCode = String.format("%05d", num);
        System.out.println(discountCode);

    }
}
