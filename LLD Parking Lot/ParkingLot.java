import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ParkingLot {
    private List<Gate> gates;
    private List<ParkingSlot> slots;
    private PricingRegistry pricingRegistry;
    private SlotAssignmentStrategy assignmentStrategy;
    private ReentrantReadWriteLock lock;

    public ParkingLot(PricingRegistry pricingRegistry, SlotAssignmentStrategy assignmentStrategy) {
        this.gates = new ArrayList<>();
        this.slots = new ArrayList<>();
        this.pricingRegistry = pricingRegistry;
        this.assignmentStrategy = assignmentStrategy;
        this.lock = new ReentrantReadWriteLock();
    }

    public void addGate(Gate gate) {
        this.gates.add(gate);
    }

    public void addSlot(ParkingSlot slot) {
        this.slots.add(slot);
    }

    public ParkingTicket park(Vehicle vehicle, Gate gate) {
        lock.writeLock().lock();
        try {
            ParkingSlot allocatedSlot = assignmentStrategy.findSlot(gate, vehicle.getType(), slots);
            if (allocatedSlot == null) {
                System.out.println("No available slot for " + vehicle.getType() + " at " + gate.getGateId());
                return null;
            }
            allocatedSlot.park();
            String ticketId = UUID.randomUUID().toString().substring(0, 8);
            System.out.println("Vehicle " + vehicle.getLicensePlate() + " parked at slot " + allocatedSlot.getSlotId());
            return new ParkingTicket(ticketId, vehicle, allocatedSlot);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public double exit(ParkingTicket ticket) {
        lock.writeLock().lock();
        try {
            ParkingSlot slot = ticket.getAllocatedSlot();
            slot.free();
            LocalDateTime exitTime = LocalDateTime.now();
            long hours = Duration.between(ticket.getEntryTime(), exitTime).toHours();
            if (hours == 0) hours = 1; // Minimum 1 hour charge
            double uncalculatedRate = pricingRegistry.getRatePerHour(slot.getSlotType());
            double totalFee = hours * uncalculatedRate;
            System.out.println("Vehicle " + ticket.getVehicle().getLicensePlate() + " exited from slot " + slot.getSlotId() + ". Total Fee: $" + totalFee);
            return totalFee;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void displayStatus() {
        lock.readLock().lock();
        try {
            System.out.println("\n--- Parking Lot Status ---");
            for (ParkingSlot slot : slots) {
                System.out.println("Slot: " + slot.getSlotId() + " (Type: " + slot.getSlotType() + ", Lvl: " + slot.getLevel() + ") - Occupied: " + slot.isOccupied());
            }
            System.out.println("--------------------------\n");
        } finally {
            lock.readLock().unlock();
        }
    }
}
