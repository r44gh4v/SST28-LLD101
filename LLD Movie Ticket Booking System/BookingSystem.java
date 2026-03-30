import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class BookingSystem {
    private Map<String, Theater> theaters = new ConcurrentHashMap<>();
    private Map<String, Show> shows = new ConcurrentHashMap<>();
    private Map<String, Booking> bookings = new ConcurrentHashMap<>();
    
    private PricingRegistry pricingRegistry = new PricingRegistry();
    private ReentrantLock lock = new ReentrantLock();

    public void addTheater(Theater theater) { theaters.put(theater.getId(), theater); }
    public void addShow(Show show) {
        show.initializeSeats(pricingRegistry);
        shows.put(show.getId(), show);
    }

    public List<ShowSeat> getAvailableSeats(String showId) {
        Show show = shows.get(showId);
        List<ShowSeat> availableSeats = new ArrayList<>();
        for (ShowSeat ss : show.getShowSeats().values()) {
            if (ss.getStatus() == SeatStatus.AVAILABLE) {
                availableSeats.add(ss);
            }
        }
        return availableSeats;
    }

    public Booking reserveSeats(String userId, String showId, List<String> seatIds) throws Exception {
        lock.lock(); // Pessimistic constraint to prevent Double Booking
        try {
            Show show = shows.get(showId);
            List<ShowSeat> seatsToReserve = new ArrayList<>();
            double totalAmount = 0;

            for (String seatId : seatIds) {
                ShowSeat showSeat = show.getShowSeats().get(seatId);
                if (showSeat.getStatus() != SeatStatus.AVAILABLE) {
                    throw new Exception("Seat " + seatId + " is already locked/booked.");
                }
                seatsToReserve.add(showSeat);
                totalAmount += showSeat.getPrice();
            }

            // Successfully secured the block, lock them temporarily
            for (ShowSeat ss : seatsToReserve) {
                ss.setStatus(SeatStatus.LOCKED);
            }

            String bookingId = "BKG-" + System.currentTimeMillis();
            Booking booking = new Booking(bookingId, userId, show, seatsToReserve, totalAmount);
            bookings.put(bookingId, booking);
            return booking;

        } finally {
            lock.unlock();
        }
    }

    public void confirmPayment(String bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking != null && booking.getStatus() == BookingStatus.CREATED) {
            booking.setStatus(BookingStatus.CONFIRMED);
            for (ShowSeat ss : booking.getBookedSeats()) {
                ss.setStatus(SeatStatus.BOOKED);
            }
            System.out.println("Payment Confirmed! Booking " + bookingId + " successful.");
        }
    }

    public void failPaymentOrTimeout(String bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking != null && booking.getStatus() == BookingStatus.CREATED) {
            booking.setStatus(BookingStatus.EXPIRED);
            for (ShowSeat ss : booking.getBookedSeats()) {
                ss.setStatus(SeatStatus.AVAILABLE); // Release lock
            }
            System.out.println("Payment Failed/Timeout. Released locked seats for " + bookingId);
        }
    }
}