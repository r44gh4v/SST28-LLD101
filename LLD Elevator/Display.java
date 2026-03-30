public class Display {
    private int currentFloor;
    private Direction direction;

    public Display() {
        this.currentFloor = 0;
        this.direction = Direction.IDLE;
    }

    public void updateDisplay(int currentFloor, Direction direction) {
        this.currentFloor = currentFloor;
        this.direction = direction;
        show();
    }

    public void show() {
        System.out.println("[Display] Floor: " + currentFloor + " | Direction: " + direction);
    }
}