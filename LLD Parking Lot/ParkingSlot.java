public class ParkingSlot {
    private String slotId;
    private int level;
    private SlotType slotType;
    private boolean isOccupied;

    public ParkingSlot(String slotId, int level, SlotType slotType) {
        this.slotId = slotId;
        this.level = level;
        this.slotType = slotType;
        this.isOccupied = false;
    }

    public String getSlotId() { return slotId; }
    public int getLevel() { return level; }
    public SlotType getSlotType() { return slotType; }
    public boolean isOccupied() { return isOccupied; }

    public void park() { this.isOccupied = true; }
    public void free() { this.isOccupied = false; }
}
