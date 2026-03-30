import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ElevatorController {
    private List<Elevator> elevators;
    private Queue<ExternalRequest> pendingRequests;

    public ElevatorController(int numElevators, int minFloor, int maxFloor) {
        this.elevators = new ArrayList<>();
        this.pendingRequests = new LinkedList<>();

        for (int i = 1; i <= numElevators; i++) {
            elevators.add(new Elevator(i, minFloor, maxFloor, 1000.0));
        }
    }

    public void submitExternalRequest(ExternalRequest request) {
        System.out.println("New External Hallway Request generated: Floor " + request.getSourceFloor() + " " + request.getDirection());
        pendingRequests.add(request);
        assignElevator();
    }

    public void submitInternalRequest(int elevatorId, InternalRequest request) {
        System.out.println("New Internal Request in Elevator " + elevatorId + " -> To Floor " + request.getDestinationFloor());
        for (Elevator elevator : elevators) {
            if (elevator.getId() == elevatorId) {
                elevator.addInternalRequest(request);
                return;
            }
        }
    }

    public void assignElevator() {
        int size = pendingRequests.size();
        for (int i = 0; i < size; i++) {
            ExternalRequest request = pendingRequests.poll();
            Elevator optimalElevator = null;
            int minDistance = Integer.MAX_VALUE;

            for (Elevator elevator : elevators) {
                // Simplified assignment: Just find the closest idle elevator for now
                if (elevator.isIdle()) {
                    int distance = Math.abs(elevator.getCurrentFloor() - request.getSourceFloor());
                    if (distance < minDistance) {
                        minDistance = distance;
                        optimalElevator = elevator;
                    }
                }
            }

            if (optimalElevator != null) {
                System.out.println("Assigning Request (Floor " + request.getSourceFloor() + ") to Elevator " + optimalElevator.getId());
                optimalElevator.addExternalRequest(request);
            } else {
                // Re-queue if no valid elevators could take it
                pendingRequests.add(request);
            }
        }
    }

    public void step() {
        assignElevator(); // try assigning queued ones again
        for (Elevator elevator : elevators) {
            if (!elevator.isIdle()) {
                elevator.move();
            }
        }
    }
}
