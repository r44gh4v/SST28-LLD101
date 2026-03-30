import java.util.List;

public interface SlotAssignmentStrategy {
    ParkingSlot findSlot(Gate gate, VehicleType vehicleType, List<ParkingSlot> availableSlots);
}
