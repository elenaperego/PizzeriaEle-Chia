package Classes.Drink;

public class Drink {
    private final int drinkId;
    private final String drinkName;
    private final double drinkPrice;

    public Drink(int id, String name, double price) {
        this.drinkId = id;
        this.drinkName = name;
        this.drinkPrice = price;
    }

    public int getId() {
        return this.drinkId;
    }

    public String getName() { return this.drinkName; }

    public double getPrice() { return this.drinkPrice; }
}
