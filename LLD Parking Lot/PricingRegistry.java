public class PricingRegistry {
    public double getRatePerHour(SlotType slotType) {
        switch (slotType) {
            case SMALL: return 10.0;
            case MEDIUM: return 20.0;
            case LARGE: return 50.0;
            default: return 0.0;
        }
    }
}
