public interface IBookingRepo {
    void save(String id, BookingRequest req, Money monthly, Money deposit);
}
