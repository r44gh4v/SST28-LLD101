import java.util.ArrayList;
import java.util.List;

public class Screen {
    private String id;
    private String name;
    private List<Seat> seats;

    public Screen(String id, String name) {
        this.id = id;
        this.name = name;
        this.seats = new ArrayList<>();
    }

    public void addSeat(Seat seat) {
        this.seats.add(seat);
    }

    public String getId() { return id; }
    public List<Seat> getSeats() { return seats; }
}