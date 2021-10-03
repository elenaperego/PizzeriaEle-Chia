package Classes.DiscountCode;

public class DiscountCode {

    private final int id;
    private final boolean isUsed;

    public DiscountCode(int id, boolean isUsed) {
        this.id = id;
        this.isUsed = isUsed;
    }

    public int getId() {
        return id;
    }

    public boolean isUsed() {
        return isUsed;
    }

}
