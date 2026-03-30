public class PricingRegistry {
    public double calculatePrice(Show show, Seat seat) {
        // Base pricing strategy based on category
        switch (seat.getCategory()) {
            case SILVER: return 100.0;
            case GOLD: return 150.0;
            case PLATINUM: return 200.0;
            default: return 100.0;
        }
        // Easy to extend for Surge Pricing handling
    }
}