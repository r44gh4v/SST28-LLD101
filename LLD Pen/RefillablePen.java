public class RefillablePen extends Pen {
    private Refill refill;

    public RefillablePen(String brand, Refill refill) {
        super(brand);
        this.refill = refill;
    }

    @Override
    public void write(String content) {
        if (!isOpen()) {
            System.out.println("Cannot write! " + getBrand() + " pen is closed. Please start() first.");
            return;
        }

        if (refill == null) {
            System.out.println("Cannot write! No refill inside the pen.");
            return;
        }

        // Each character costs 1 unit of ink
        double requiredInk = content.length();

        if (refill.consumeInk(requiredInk)) {
            System.out.println("Writing using " + getBrand() + " pen (" + refill.getInk().getColor() + "): " + content);
        } else {
            System.out.println("Cannot write! Insufficient ink in the refill.");
        }
    }

    public void changeRefill(Refill newRefill) {
        this.refill = newRefill;
        System.out.println(getBrand() + " pen refill changed successfully.");
    }

    public Refill getRefill() {
        return refill;
    }
}
