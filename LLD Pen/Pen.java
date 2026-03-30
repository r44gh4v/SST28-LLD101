public abstract class Pen {
    private boolean isOpen;
    private final String brand;

    public Pen(String brand) {
        this.brand = brand;
        this.isOpen = false;
    }

    public void start() {
        if (isOpen) {
            System.out.println(brand + " pen is already open.");
        } else {
            isOpen = true;
            System.out.println(brand + " pen capped removed. Pen is ready.");
        }
    }

    public void close() {
        if (!isOpen) {
            System.out.println(brand + " pen is already closed.");
        } else {
            isOpen = false;
            System.out.println(brand + " pen capped. Pen is closed.");
        }
    }

    public boolean isOpen() {
        return isOpen;
    }

    public String getBrand() {
        return brand;
    }

    // Template method for writing. Specific details handled by implementers.
    public abstract void write(String content);
}
