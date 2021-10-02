package Classes.Drink;

public class Drink {
    private final long drinkId;
    private final String drinkName;
    private final double drinkPrice;

    public Drink(long id, String name, double price) {
        this.drinkId = id;
        this.drinkName = name;
        this.drinkPrice = price;
    }

    public long getId() {
        return this.drinkId;
    }

    public String getName() { return this.drinkName; }

    public double getPrice() { return this.drinkPrice; }
}
