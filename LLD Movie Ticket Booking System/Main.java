import java.time.LocalDateTime;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Initializing Movie Ticket Booking System...");
        BookingSystem system = new BookingSystem();

        // 1. Setup Models
        Movie movie = new Movie("M1", "Dune: Part Two", 166);
        Theater theater = new Theater("T1", "PVR IMAX", "Mumbai");
        Screen screen = new Screen("SC1", "Screen 1");
        theater.addScreen(screen);
        system.addTheater(theater);

        // Map layout
        screen.addSeat(new Seat("A1", 1, 1, SeatCategory.SILVER));
        screen.addSeat(new Seat("A2", 1, 2, SeatCategory.SILVER));
        screen.addSeat(new Seat("B1", 2, 1, SeatCategory.GOLD));
        screen.addSeat(new Seat("B2", 2, 2, SeatCategory.GOLD));

        // Create Show & Generate Pricing
        Show show = new Show("SH1", movie, screen, LocalDateTime.now().plusDays(1));
        system.addShow(show);

        // 2. Multithreaded Execution to test Concurrency (Pessimistic Locking)
        Runnable user1 = () -> {
            try {
                System.out.println("[User1] Attempting to book A1 and A2...");
                Booking booking = system.reserveSeats("U1", "SH1", Arrays.asList("A1", "A2"));
                System.out.println("[User1] Success! Proceeding to Payment... Locked Bkg ID: " + booking.getId());
                Thread.sleep(50); // Simulate payment delay
                system.confirmPayment(booking.getId());
            } catch (Exception e) {
                System.out.println("[User1] Booking Failed: " + e.getMessage());
            }
        };

        Runnable user2 = () -> {
            try {
                Thread.sleep(10); // Offset slightly to guarantee conflict testing
                System.out.println("[User2] Attempting to book A2 and B1...");
                Booking booking = system.reserveSeats("U2", "SH1", Arrays.asList("A2", "B1"));
                system.confirmPayment(booking.getId());
            } catch (Exception e) {
                System.out.println("[User2] Booking Failed: " + e.getMessage());
            }
        };

        Thread t1 = new Thread(user1);
        Thread t2 = new Thread(user2);
        
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Output Final Status Snapshot
        System.out.println("\n--- Final Seat Status Check ---");
        for (ShowSeat ss : show.getShowSeats().values()) {
            System.out.println("Seat " + ss.getSeat().getId() + " [" + ss.getSeat().getCategory() + "] -> " + ss.getStatus());
        }
    }
}