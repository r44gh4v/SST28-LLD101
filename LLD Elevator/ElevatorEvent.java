public class ElevatorEvent {
    public enum EventType {
        WEIGHT_CHANGE,
        MAINTENANCE_TOGGLE
    }

    private EventType type;
    private int carId;
    private double value;

    public ElevatorEvent(EventType type, int carId, double value) {
        this.type = type;
        this.carId = carId;
        this.value = value;
    }

    public EventType getType() { return type; }
    public int getCarId() { return carId; }
    public double getValue() { return value; }
}
