public enum SlotType {
    SMALL,
    MEDIUM,
    LARGE;

    public boolean canFit(VehicleType vehicleType) {
        switch (this) {
            case SMALL:
                return vehicleType == VehicleType.TWO_WHEELER;
            case MEDIUM:
                return vehicleType == VehicleType.TWO_WHEELER || vehicleType == VehicleType.CAR;
            case LARGE:
                return true; // Large can fit everything
            default:
                return false;
        }
    }
}
