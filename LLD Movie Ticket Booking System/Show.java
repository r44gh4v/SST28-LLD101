import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Show {
    private String id;
    private Movie movie;
    private Screen screen;
    private LocalDateTime startTime;
    private Map<String, ShowSeat> showSeats;

    public Show(String id, Movie movie, Screen screen, LocalDateTime startTime) {
        this.id = id;
        this.movie = movie;
        this.screen = screen;
        this.startTime = startTime;
        this.showSeats = new HashMap<>();
    }

    public void initializeSeats(PricingRegistry pricingRegistry) {
        for (Seat seat : screen.getSeats()) {
            double price = pricingRegistry.calculatePrice(this, seat);
            showSeats.put(seat.getId(), new ShowSeat(seat, price));
        }
    }

    public String getId() { return id; }
    public Movie getMovie() { return movie; }
    public Screen getScreen() { return screen; }
    public Map<String, ShowSeat> getShowSeats() { return showSeats; }
}