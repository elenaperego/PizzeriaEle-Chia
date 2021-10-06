package Insert;

import Classes.PizzaTopping.PizzaTopping;
import Mappers.ConnectionImpl;
import Mappers.PizzaToppingDataMapper;

import java.sql.Connection;
import java.util.concurrent.atomic.AtomicInteger;

public class InsertToppings {
    static AtomicInteger counter = new AtomicInteger();
    Connection conn = ConnectionImpl.getConnection();

    public InsertToppings() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
    }

    public void insert() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        PizzaToppingDataMapper mapper = new PizzaToppingDataMapper(conn);

        for(int i = 1; i < 9; i++) {
            PizzaTopping p1 = new PizzaTopping(counter.getAndIncrement(), i, "pomodoro", 2);
            mapper.insert(p1);
        }

        for(int i = 1; i < 11; i++){
            PizzaTopping p1 = new PizzaTopping(counter.getAndIncrement(), i, "mozzarella", 2);
            mapper.insert(p1);
        }

        PizzaTopping p1 = new PizzaTopping(counter.getAndIncrement(), 2, "patatine", 1);
        mapper.insert(p1);

        PizzaTopping p2 = new PizzaTopping(counter.getAndIncrement(), 3, "fontina", 3);
        mapper.insert(p2);

        PizzaTopping p3 = new PizzaTopping(counter.getAndIncrement(), 3, "gorgonzola", 4);
        mapper.insert(p3);

        PizzaTopping p4 = new PizzaTopping(counter.getAndIncrement(), 10, "gorgonzola", 4);
        mapper.insert(p4);

        PizzaTopping p5 = new PizzaTopping(counter.getAndIncrement(), 3, "grana", 3);
        mapper.insert(p5);

        PizzaTopping p6 = new PizzaTopping(counter.getAndIncrement(), 9, "grana", 3);
        mapper.insert(p6);

        PizzaTopping p7 = new PizzaTopping(counter.getAndIncrement(), 4, "prosciutto san daniele", 4);
        mapper.insert(p7);

        PizzaTopping p8 = new PizzaTopping(counter.getAndIncrement(), 5, "rucola", 2);
        mapper.insert(p8);

        PizzaTopping p9 = new PizzaTopping(counter.getAndIncrement(), 5, "stracchino", 3);
        mapper.insert(p9);

        PizzaTopping p10 = new PizzaTopping(counter.getAndIncrement(), 6, "funghi", 3);
        mapper.insert(p10);

        PizzaTopping p11 = new PizzaTopping(counter.getAndIncrement(), 6, "tartufo", 4);
        mapper.insert(p11);

        PizzaTopping p12 = new PizzaTopping(counter.getAndIncrement(), 10, "tartufo", 4);
        mapper.insert(p12);

        PizzaTopping p13 = new PizzaTopping(counter.getAndIncrement(), 7, "salame piccante", 3);
        mapper.insert(p13);

        PizzaTopping p14 = new PizzaTopping(counter.getAndIncrement(), 8, "prosciutto cotto", 3);
        mapper.insert(p14);

        PizzaTopping p15 = new PizzaTopping(counter.getAndIncrement(), 9, "prosciutto cotto", 3);
        mapper.insert(p15);

        PizzaTopping p16 = new PizzaTopping(counter.getAndIncrement(), 8, "carciofi", 2);
        mapper.insert(p16);

        PizzaTopping p17 = new PizzaTopping(counter.getAndIncrement(), 8, "salsiccia", 3);
        mapper.insert(p17);

        PizzaTopping p18 = new PizzaTopping(counter.getAndIncrement(), 9, "ricotta", 2);
        mapper.insert(p18);

        PizzaTopping p19 = new PizzaTopping(counter.getAndIncrement(), 10, "speck", 4);
        mapper.insert(p19);
    }
}
