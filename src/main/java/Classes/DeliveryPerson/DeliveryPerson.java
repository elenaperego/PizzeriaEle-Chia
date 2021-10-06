package Classes.DeliveryPerson;

public class DeliveryPerson {
    private int deliveryPersonId;
    private boolean isGirl;
    private int areaCode;
    private boolean isAvailable;

    public DeliveryPerson(int deliveryPersonId, boolean isGirl, int areaCode, boolean isAvailable) {
        this.deliveryPersonId = deliveryPersonId;
        this.isGirl = isGirl;
        this.areaCode = areaCode;
        this.isAvailable = isAvailable;
    }

    public int getDeliveryPersonId() {
        return deliveryPersonId;
    }

    public boolean isGirl() {
        return isGirl;
    }

    public int getAreaCode() {
        return areaCode;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

}
