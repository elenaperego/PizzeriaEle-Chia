package Classes.DiscountCode;

public class DiscountCode {

    private final int id;
    private boolean isUsed;
    private final long discountCode;

    public DiscountCode(int id, long discountCode, boolean isUsed) {
        this.id = id;
        this.discountCode = discountCode;
        this.isUsed = isUsed;
    }

    public int getId() {
        return id;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public long getDiscountCode() { return discountCode; }

    public void setUsed(boolean used) {
        isUsed = used;
    }
}
