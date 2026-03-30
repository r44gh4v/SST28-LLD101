import java.time.LocalDateTime;
import java.util.List;

public class Booking {
    private String id;
    private String userId;
    private Show show;
    private List<ShowSeat> bookedSeats;
    private BookingStatus status;
    private LocalDateTime bookingTime;
    private double totalAmount;

    public Booking(String id, String userId, Show show, List<ShowSeat> bookedSeats, double totalAmount) {
        this.id = id;
        this.userId = userId;
        this.show = show;
        this.bookedSeats = bookedSeats;
        this.status = BookingStatus.CREATED;
        this.bookingTime = LocalDateTime.now();
        this.totalAmount = totalAmount;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public String getId() { return id; }
    public List<ShowSeat> getBookedSeats() { return bookedSeats; }
    public BookingStatus getStatus() { return status; }
    public Show getShow() { return show; }
}