public class Button {
    private int floor;
    private boolean isPressed;

    public Button(int floor) {
        this.floor = floor;
        this.isPressed = false;
    }

    public void press() {
        this.isPressed = true;
        System.out.println("Button for floor " + floor + " pressed.");
    }

    public void reset() {
        this.isPressed = false;
    }

    public int getFloor() { return floor; }
    public boolean isPressed() { return isPressed; }
}