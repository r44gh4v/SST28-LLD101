public class ShowSeat {
    private Seat seat;
    private SeatStatus status;
    private double price;

    public ShowSeat(Seat seat, double price) {
        this.seat = seat;
        this.price = price;
        this.status = SeatStatus.AVAILABLE;
    }

    public Seat getSeat() { return seat; }
    public SeatStatus getStatus() { return status; }
    public double getPrice() { return price; }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }
}