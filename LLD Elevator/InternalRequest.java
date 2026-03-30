public class InternalRequest implements Request {
    private int destinationFloor;
    private Direction direction;

    public InternalRequest(int destinationFloor, Direction direction) {
        this.destinationFloor = destinationFloor;
        this.direction = direction;
    }

    @Override
    public int getSourceFloor() { return -1; }

    @Override
    public int getDestinationFloor() { return destinationFloor; }

    @Override
    public Direction getDirection() { return direction; }
}