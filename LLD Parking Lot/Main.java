public class Main {
    public static void main(String[] args) {
        System.out.println("Initializing Thread-Safe Multilevel Parking Lot...");

        PricingRegistry pricingRegistry = new PricingRegistry();
        SlotAssignmentStrategy strategy = new NearestSlotAssignmentStrategy();
        ParkingLot lot = new ParkingLot(pricingRegistry, strategy);

        // Add Gates
        Gate gate1 = new Gate("G1", 1);
        Gate gate2 = new Gate("G2", 2);
        lot.addGate(gate1);
        lot.addGate(gate2);

        // Add Slots
        lot.addSlot(new ParkingSlot("S1_1", 1, SlotType.SMALL));
        lot.addSlot(new ParkingSlot("S1_2", 1, SlotType.MEDIUM));
        lot.addSlot(new ParkingSlot("S2_1", 2, SlotType.LARGE));
        lot.addSlot(new ParkingSlot("S2_2", 2, SlotType.LARGE));

        // Test multi-threading to ensure write locks work
        Runnable r1 = () -> {
            Vehicle v1 = new Vehicle("CAR-123", VehicleType.CAR);
            lot.park(v1, gate1);
        };
        Runnable r2 = () -> {
            Vehicle v2 = new Vehicle("CAR-456", VehicleType.CAR);
            lot.park(v2, gate1); // Should hit S2_1 or S2_2 depending on concurrency
        };
        Runnable r3 = () -> {
            Vehicle v3 = new Vehicle("BUS-789", VehicleType.BUS);
            lot.park(v3, gate2); // Should definitely lock and take S2_1 or S2_2
        };

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lot.displayStatus();
    }
}
