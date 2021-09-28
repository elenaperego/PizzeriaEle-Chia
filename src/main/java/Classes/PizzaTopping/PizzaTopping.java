package Classes.PizzaTopping;

public class PizzaTopping {
    private final int id;
    private final int pizzaId;
    private final String name;
    private final double price;


    public PizzaTopping(int id, int pizzaId, String name, double price) {
        this.id = id;
        this.pizzaId = pizzaId;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getPizzaId() {
        return pizzaId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
