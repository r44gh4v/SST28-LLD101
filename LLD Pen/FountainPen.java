public class FountainPen extends Pen {
    private final Ink ink;
    private final Nib nib;
    private double currentLevel;
    private final double maxCapacity;

    public FountainPen(String brand, Ink ink, Nib nib, double maxCapacity) {
        super(brand);
        this.ink = ink;
        this.nib = nib;
        this.maxCapacity = maxCapacity;
        this.currentLevel = 0.0; // Starts empty
    }

    @Override
    public void write(String content) {
        if (!isOpen()) {
            System.out.println("Cannot write! " + getBrand() + " pen is closed. Please start() first.");
            return;
        }

        // Each character costs 1 unit of ink
        double requiredInk = content.length();

        if (currentLevel >= requiredInk) {
            currentLevel -= requiredInk;
            System.out.println("Writing using " + getBrand() + " fountain pen (" + ink.getColor() + "): " + content);
        } else {
            System.out.println("Cannot write! Insufficient ink in the tank.");
        }
    }

    public void refillInk(double amount) {
        if (amount < 0) {
            System.out.println("Invalid refill amount.");
            return;
        }

        if (currentLevel + amount > maxCapacity) {
            currentLevel = maxCapacity;
            System.out.println("Tank filled to max capacity (" + maxCapacity + "). Some ink spilled.");
        } else {
            currentLevel += amount;
            System.out.println("Added " + amount + " units of ink. Current level: " + currentLevel);
        }
    }

    public double getCurrentLevel() {
        return currentLevel;
    }

    public double getMaxCapacity() {
        return maxCapacity;
    }
}
