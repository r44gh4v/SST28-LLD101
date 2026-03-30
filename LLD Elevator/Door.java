public class Door {
    private boolean isOpen;

    public Door() {
        this.isOpen = false;
    }

    public void openDoor() {
        this.isOpen = true;
        System.out.println("Doors are OPEN.");
    }

    public void closeDoor() {
        this.isOpen = false;
        System.out.println("Doors are CLOSED.");
    }

    public boolean isOpen() {
        return isOpen;
    }
}