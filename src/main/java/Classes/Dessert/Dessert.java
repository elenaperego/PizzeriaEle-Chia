package Classes.Dessert;

public class Dessert {
    private final long dessertId;
    private final String dessertName;
    private final double dessertPrice;

    public Dessert(long id, String name, double price) {
        this.dessertId = id;
        this.dessertName = name;
        this.dessertPrice = price;
    }

    public long getId() {
        return dessertId;
    }

    public String getName() { return dessertName; }

    public double getPrice() { return dessertPrice; }
}
