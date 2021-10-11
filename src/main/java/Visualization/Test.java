package Visualization;

import java.util.Calendar;
import java.util.Date;

public class Test {

    static Date startDate;
    static Date currentDate;

    public static void main(String[] args) {

        Calendar currentTime = Calendar.getInstance();
        long timeInSecs = currentTime.getTimeInMillis();
        startDate = new Date();
        currentDate = startDate;
        Date afterAdding1Min = new Date(timeInSecs + (1 * 60 * 1000));

        System.out.println(startDate);

        while (!is1MinPassed(currentDate, afterAdding1Min)) {
            System.out.println(currentDate.toString());
            currentDate = new Date();
        }
    }

    public static boolean is1MinPassed(Date currentDate, Date after1min) {

        if (currentDate.equals(after1min)) {
            return true;
        } else {
            return false;
        }
    }
}
