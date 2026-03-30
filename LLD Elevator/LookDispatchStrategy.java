import java.util.List;

public class LookDispatchStrategy implements DispatchStrategy {
    
    @Override
    public ElevatorCar assignRequest(List<ElevatorCar> cars, ExternalRequest request) {
        ElevatorCar bestCar = null;
        int minDistance = Integer.MAX_VALUE;

        // Pass 1: Try to assign to a car heading towards the request natively
        for (ElevatorCar car : cars) {
            if (car.getState() == ElevatorState.MAINTENANCE) continue;

            int distance = Math.abs(car.getCurrentFloor() - request.getFloor());
            boolean isMovingTowards = false;

            if (car.getCurrentDirection() == Direction.IDLE) {
                isMovingTowards = true;
            } else if (car.getCurrentDirection() == Direction.UP && request.getFloor() >= car.getCurrentFloor() && request.getDirection() == Direction.UP) {
                isMovingTowards = true;
            } else if (car.getCurrentDirection() == Direction.DOWN && request.getFloor() <= car.getCurrentFloor() && request.getDirection() == Direction.DOWN) {
                isMovingTowards = true;
            }

            if (isMovingTowards && distance < minDistance) {
                minDistance = distance;
                bestCar = car;
            }
        }

        // Pass 2: Fallback to the nearest car period (sweep loop resolution)
        if (bestCar == null) {
            for (ElevatorCar car : cars) {
                if (car.getState() == ElevatorState.MAINTENANCE) continue;
                int distance = Math.abs(car.getCurrentFloor() - request.getFloor());
                if (distance < minDistance) {
                    minDistance = distance;
                    bestCar = car;
                }
            }
        }

        return bestCar;
    }
}
