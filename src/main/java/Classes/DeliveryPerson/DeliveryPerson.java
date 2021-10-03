package Classes.DeliveryPerson;

public class DeliveryPerson {
    private int deliveryPersonId;
    private boolean isGirl;
    private int areaCode;

    public DeliveryPerson(int deliveryPersonId, boolean isGirl, int areaCode) {
        this.deliveryPersonId = deliveryPersonId;
        this.isGirl = isGirl;
        this.areaCode = areaCode;
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

}
