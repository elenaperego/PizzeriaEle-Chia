package Classes.Dessert;

public class Dessert {
    private final int dessertId;
    private final String dessertName;
    private final double dessertPrice;

    public Dessert(int id, String name, double price) {
        this.dessertId = id;
        this.dessertName = name;
        this.dessertPrice = price;
    }

    public int getId() {
        return dessertId;
    }

    public String getName() { return dessertName; }

    public double getPrice() { return dessertPrice; }
}
