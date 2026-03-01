public class FakeBookingRepo implements IBookingRepo {
    @Override
    public void save(String id, BookingRequest req, Money monthly, Money deposit) {
        System.out.println("Saved booking: " + id);
    }
}
