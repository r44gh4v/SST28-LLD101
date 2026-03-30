import java.util.List;

public class NearestSlotAssignmentStrategy implements SlotAssignmentStrategy {
    @Override
    public ParkingSlot findSlot(Gate gate, VehicleType vehicleType, List<ParkingSlot> allSlots) {
        ParkingSlot bestSlot = null;
        int minDistance = Integer.MAX_VALUE;

        for (ParkingSlot slot : allSlots) {
            if (!slot.isOccupied() && slot.getSlotType().canFit(vehicleType)) {
                int distance = Math.abs(slot.getLevel() - gate.getLevel());
                if (distance < minDistance) {
                    minDistance = distance;
                    bestSlot = slot;
                }
            }
        }
        return bestSlot;
    }
}
