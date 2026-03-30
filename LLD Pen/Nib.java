public class Nib {
    private final double radius;

    public Nib(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "Nib(" + radius + "mm)";
    }
}
