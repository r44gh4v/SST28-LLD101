import java.util.Collections;
import java.util.TreeSet;

public class Elevator {
    private int id;
    private int currentFloor;
    private Direction direction;
    private ElevatorState state;
    private double capacity;
    private double currentLoad;

    private TreeSet<Integer> upStops;
    private TreeSet<Integer> downStops;

    private Panel panel;
    private Door door;
    private Display display;

    public Elevator(int id, int minFloor, int maxFloor, double capacity) {
        this.id = id;
        this.currentFloor = 0;
        this.direction = Direction.IDLE;
        this.state = ElevatorState.IDLE;
        this.capacity = capacity;
        this.currentLoad = 0.0;

        this.upStops = new TreeSet<>();
        this.downStops = new TreeSet<>(Collections.reverseOrder());

        this.panel = new Panel(minFloor, maxFloor);
        this.door = new Door();
        this.display = new Display();
    }

    public void addInternalRequest(InternalRequest request) {
        int floor = request.getDestinationFloor();
        panel.pressButton(floor);
        
        if (floor > currentFloor) {
            upStops.add(floor);
        } else if (floor < currentFloor) {
            downStops.add(floor);
        } else {
            openDoor();
            panel.resetButton(floor);
        }
        updateDirectionAndState();
    }

    public void addExternalRequest(ExternalRequest request) {
        int floor = request.getSourceFloor();
        if (floor > currentFloor) {
            upStops.add(floor);
        } else if (floor < currentFloor) {
            downStops.add(floor);
        } else { // Exactly on this floor
            if (this.door.isOpen()) {
                 // Already open, people can get in
                 System.out.println("Elevator " + id + " is already at floor " + floor);
            } else {
                 openDoor();
            }
        }
        updateDirectionAndState();
    }

    private void updateDirectionAndState() {
        if (direction == Direction.IDLE || (upStops.isEmpty() && downStops.isEmpty())) {
            if (!upStops.isEmpty()) {
                direction = Direction.UP;
                state = ElevatorState.MOVING;
            } else if (!downStops.isEmpty()) {
                direction = Direction.DOWN;
                state = ElevatorState.MOVING;
            } else {
                direction = Direction.IDLE;
                state = ElevatorState.IDLE;
            }
        }
    }

    public void move() {
        if (currentLoad > capacity) {
            System.out.println("Elevator " + id + " OVERLOADED. Cannot move.");
            return;
        }

        if (door.isOpen()) {
            closeDoor();
        }

        if (direction == Direction.UP && !upStops.isEmpty()) {
            currentFloor++;
            System.out.println("\nElevator " + id + " moved ^ UP. Now at floor " + currentFloor);
            display.updateDisplay(currentFloor, direction);

            if (upStops.contains(currentFloor)) {
                openDoor();
                upStops.remove(currentFloor);
                panel.resetButton(currentFloor);
            }
        } else if (direction == Direction.DOWN && !downStops.isEmpty()) {
            currentFloor--;
            System.out.println("\nElevator " + id + " moved v DOWN. Now at floor " + currentFloor);
            display.updateDisplay(currentFloor, direction);

            if (downStops.contains(currentFloor)) {
                openDoor();
                downStops.remove(currentFloor);
                panel.resetButton(currentFloor);
            }
        }

        // Change directions if reached end of travel line
        if (direction == Direction.UP && upStops.isEmpty()) {
            if (!downStops.isEmpty()) {
                direction = Direction.DOWN;
            } else {
                direction = Direction.IDLE;
                state = ElevatorState.IDLE;
            }
        } else if (direction == Direction.DOWN && downStops.isEmpty()) {
            if (!upStops.isEmpty()) {
                direction = Direction.UP;
            } else {
                direction = Direction.IDLE;
                state = ElevatorState.IDLE;
            }
        }
    }

    public void openDoor() {
        System.out.println("Elevator " + id + " ARRIVED at floor " + currentFloor);
        door.openDoor();
    }

    public void closeDoor() {
        door.closeDoor();
    }

    public boolean isIdle() {
        return state == ElevatorState.IDLE && upStops.isEmpty() && downStops.isEmpty();
    }

    public int getId() { return id; }
    public int getCurrentFloor() { return currentFloor; }
    public Direction getDirection() { return direction; }
    public ElevatorState getState() { return state; }
}