import java.util.List;

public interface DispatchStrategy {
    ElevatorCar assignRequest(List<ElevatorCar> cars, ExternalRequest request);
}
