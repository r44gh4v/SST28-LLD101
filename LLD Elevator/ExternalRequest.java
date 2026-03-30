public class ExternalRequest implements Request {
    private int sourceFloor;
    private Direction direction;

    public ExternalRequest(int sourceFloor, Direction direction) {
        this.sourceFloor = sourceFloor;
        this.direction = direction;
    }

    @Override
    public int getSourceFloor() { return sourceFloor; }

    @Override
    public int getDestinationFloor() { return -1; }

    @Override
    public Direction getDirection() { return direction; }
}
