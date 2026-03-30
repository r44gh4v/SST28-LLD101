public class Main {
    public static void main(String[] args) {
        System.out.println("Starting Elevator System Simulation...");
        
        // Controller with 2 elevators, serving floors 0 to 10
        ElevatorController controller = new ElevatorController(2, 0, 10);

        // A user at floor 3 wants to go UP
        controller.submitExternalRequest(new ExternalRequest(3, Direction.UP));

        // Processing a few steps
        System.out.println("\n--- Step 1 to 4 ---");
        for (int i = 0; i < 4; i++) {
            controller.step();
        }

        // Inside Elevator 1, the user presses Button for Floor 7
        controller.submitInternalRequest(1, new InternalRequest(7, Direction.UP));

        // Move until it arrives at 7
        System.out.println("\n--- Step 5 to 10 ---");
        for (int i = 0; i < 6; i++) {
            controller.step();
        }
    }
}
