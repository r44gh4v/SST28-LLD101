import java.util.ArrayList;
import java.util.List;

public class Sensor {
    private static Sensor instance;
    private List<ElevatorObserver> observers = new ArrayList<>();

    private Sensor() {}

    public static Sensor getInstance() {
        if (instance == null) {
            instance = new Sensor();
        }
        return instance;
    }

    public void attach(ElevatorObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void detach(ElevatorObserver observer) {
        observers.remove(observer);
    }

    public void triggerEvent(ElevatorEvent event) {
        System.out.println("[Sensor] Event Emitted: " + event.getType() + " for Car " + event.getCarId() + " (Value: " + event.getValue() + ")");
        for (ElevatorObserver observer : observers) {
            observer.onEvent(event);
        }
    }
}
